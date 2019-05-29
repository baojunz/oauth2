-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- 主机： db
-- 生成日期： 2019-05-29 10:07:48
-- 服务器版本： 5.6.44
-- PHP 版本： 7.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

--
-- 数据库： `oauth2`
--

-- --------------------------------------------------------

--
-- 表的结构 `clientdetails`
--

CREATE TABLE `clientdetails` (
  `appId` varchar(128) NOT NULL,
  `resourceIds` varchar(256) DEFAULT NULL,
  `appSecret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `grantTypes` varchar(256) DEFAULT NULL,
  `redirectUrl` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) DEFAULT NULL,
  `autoApproveScopes` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `oauth_access_token`
--

CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `oauth_approvals`
--

CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` datetime DEFAULT NULL,
  `lastModifiedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `oauth_client_details`
--

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `oauth_client_details`
--

INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES
(''client'', NULL, ''$2a$10$M4NhY2X4anHW4X2v1dqJTuhe3tjbfOQiH3P9yfqWDvukZSeZfbpPu'', ''all'', ''authorization_code'', ''http://www.baidu.com'', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `oauth_client_token`
--

CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `oauth_code`
--

CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `oauth_refresh_token`
--

CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转储表的索引
--

--
-- 表的索引 `clientdetails`
--
ALTER TABLE `clientdetails`
  ADD PRIMARY KEY (`appId`);

--
-- 表的索引 `oauth_access_token`
--
ALTER TABLE `oauth_access_token`
  ADD PRIMARY KEY (`authentication_id`);

--
-- 表的索引 `oauth_client_details`
--
ALTER TABLE `oauth_client_details`
  ADD PRIMARY KEY (`client_id`);

--
-- 表的索引 `oauth_client_token`
--
ALTER TABLE `oauth_client_token`
  ADD PRIMARY KEY (`authentication_id`);
COMMIT;