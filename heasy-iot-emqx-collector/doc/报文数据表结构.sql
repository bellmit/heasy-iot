CREATE TABLE `client_connected` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `clientid` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `node` varchar(50) DEFAULT NULL,
  `timestamp` bigint DEFAULT NULL,
  `peername` varchar(50) DEFAULT NULL,
  `sockname` varchar(50) DEFAULT NULL,
  `proto_name` varchar(20) DEFAULT NULL,
  `proto_ver` float DEFAULT NULL,
  `user_properties` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `client_disconnected` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `clientid` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `node` varchar(50) DEFAULT NULL,
  `timestamp` bigint DEFAULT NULL,
  `peername` varchar(50) DEFAULT NULL,
  `sockname` varchar(50) DEFAULT NULL,
  `reason` varchar(50) DEFAULT NULL,
  `disconnected_at` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `session_subscribed` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `clientid` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `node` varchar(50) DEFAULT NULL,
  `timestamp` bigint DEFAULT NULL,
  `peerhost` varchar(50) DEFAULT NULL,
  `topic` varchar(100) DEFAULT NULL,
  `qos` tinyint(1) DEFAULT 0,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `message_delivered` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `clientid` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `node` varchar(50) DEFAULT NULL,
  `timestamp` bigint DEFAULT NULL,
  `peerhost` varchar(50) DEFAULT NULL,
  `topic` varchar(100) DEFAULT NULL,
  `qos` tinyint(1) DEFAULT 0,
  `payload` varchar(1000) DEFAULT NULL,
  `mid` varchar(50) DEFAULT NULL,
  `publish_received_at` bigint DEFAULT NULL,
  `from_username` varchar(50) DEFAULT NULL,
  `from_clientid` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `message_acked` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `clientid` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `node` varchar(50) DEFAULT NULL,
  `timestamp` bigint DEFAULT NULL,
  `peerhost` varchar(50) DEFAULT NULL,
  `topic` varchar(100) DEFAULT NULL,
  `qos` tinyint(1) DEFAULT 0,
  `payload` varchar(1000) DEFAULT NULL,
  `mid` varchar(50) DEFAULT NULL,
  `publish_received_at` bigint DEFAULT NULL,
  `from_username` varchar(50) DEFAULT NULL,
  `from_clientid` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `message_dropped` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `clientid` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `node` varchar(50) DEFAULT NULL,
  `timestamp` bigint DEFAULT NULL,
  `peerhost` varchar(50) DEFAULT NULL,
  `topic` varchar(100) DEFAULT NULL,
  `qos` tinyint(1) DEFAULT 0,
  `payload` varchar(1000) DEFAULT NULL,
  `mid` varchar(50) DEFAULT NULL,
  `publish_received_at` bigint DEFAULT NULL,
  `reason` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;