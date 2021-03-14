package com.heasy.iot.emqx.client.sdk;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PasswordFinder;

public class SSLUtil {
	/**
     * 用证书和私钥配置sslContext
     *
     * @param caCrtFile
     *            CA证书（验证连接）
     * @param crtFile
     *            发给对方的证书
     * @param keyFile
     *            pem 私钥（请求连接的消息是用公钥加密的，需要用私钥解密）
     * @param password
     *            私钥密码
     */
    public static SSLSocketFactory getSocketFactoryByCert(final EmqxConfig config) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        // 加载CA证书（用于验证的根证书）
        PEMReader reader =
            new PEMReader(new InputStreamReader(new ByteArrayInputStream(Files.readAllBytes(Paths.get(config.getCafile())))));
        X509Certificate caCert = (X509Certificate)reader.readObject();
        reader.close();

        // 加载自己的证书，用于发送给客户端
        reader = new PEMReader(new InputStreamReader(new ByteArrayInputStream(Files.readAllBytes(Paths.get(config.getClientCertFile())))));
        X509Certificate cert = (X509Certificate)reader.readObject();
        reader.close();

        // 加载私钥
        reader = new PEMReader(new InputStreamReader(new ByteArrayInputStream(Files.readAllBytes(Paths.get(config.getClientKeyFile())))), 
        	new PasswordFinder() {
				@Override
				public char[] getPassword() {
					return config.getClientKeyFilePassword().toCharArray();
				}
			});
        KeyPair key = (KeyPair)reader.readObject();
        reader.close();

        // 用CA证书创建TrustManagerFactory
        KeyStore caKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        caKeyStore.load(null, null);
        caKeyStore.setCertificateEntry("ca-certificate", caCert);
        TrustManagerFactory tmFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmFactory.init(caKeyStore);

        // 用证书和私钥创建KeyManagerFactory
        KeyStore clientKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        clientKeyStore.load(null, null);
        clientKeyStore.setCertificateEntry("certificate", cert);
        clientKeyStore.setKeyEntry("private-key", key.getPrivate(), config.getClientKeyFilePassword().toCharArray(),
            new java.security.cert.Certificate[] {cert});
        KeyManagerFactory kmFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmFactory.init(clientKeyStore, config.getClientKeyFilePassword().toCharArray());

        SSLContext context = SSLContext.getInstance(config.getProtocol());
        // kmFactory用于发送关键信息让服务端校验，tmFactory用于校验服务端的证书。双向认证
        context.init(kmFactory.getKeyManagers(), tmFactory.getTrustManagers(), new SecureRandom());
        return context.getSocketFactory();
    }
    
}
