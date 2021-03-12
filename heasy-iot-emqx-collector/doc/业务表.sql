CREATE TABLE `emqx_clients` (
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
  `status` tinyint(1) DEFAULT 1 COMMENT '1:online, 2:offline',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `client_connected_clientid` (`clientid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `emqx_subscribers` (
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

CREATE TABLE `emqx_messages` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `clientid` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `node` varchar(50) DEFAULT NULL,
  `timestamp` bigint DEFAULT NULL,
  `peerhost` varchar(50) DEFAULT NULL,
  `topic` varchar(100) DEFAULT NULL,
  `qos` tinyint(1) DEFAULT 0,
  `payload` varchar(1000) DEFAULT NULL,
  `message_id` varchar(50) DEFAULT NULL,
  `from_username` varchar(50) DEFAULT NULL,
  `from_clientid` varchar(50) DEFAULT NULL,
  `reason` varchar(50) DEFAULT NULL,
  `status` tinyint(1) DEFAULT 1 COMMENT '1:delivered, 2:acked',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;