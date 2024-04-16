-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: discuz
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `advise`
--

DROP TABLE IF EXISTS `advise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `advise` (
  `adviseId` int(11) NOT NULL AUTO_INCREMENT COMMENT '举报和建议id',
  `describe` varchar(120) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `content` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  `userId` int(11) DEFAULT NULL COMMENT '涉及的用户id',
  `topicId` int(11) DEFAULT NULL COMMENT '涉及的帖子id\n',
  `commentId` int(11) DEFAULT NULL COMMENT '涉及的评论id\n',
  `result` tinyint(4) NOT NULL DEFAULT '1' COMMENT '处理结果 1未处理 2已处理 ',
  `resultStr` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '处理结果描述',
  `adviseUserId` int(11) NOT NULL COMMENT '发起人Id',
  `adminId` int(11) DEFAULT NULL COMMENT '处理的管理员Id',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `handleTime` datetime DEFAULT NULL COMMENT '处理时间',
  `category` tinyint(4) NOT NULL COMMENT '1建议,2举报',
  PRIMARY KEY (`adviseId`)
) ENGINE=InnoDB AUTO_INCREMENT=10015 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='举报和建议';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advise`
--

LOCK TABLES `advise` WRITE;
/*!40000 ALTER TABLE `advise` DISABLE KEYS */;
INSERT INTO `advise` VALUES (10001,'asdad','11111',NULL,1,NULL,2,NULL,10001,10001,'1997-11-10 00:00:00','2022-05-05 09:34:51',1),(10002,'举报了帖子',NULL,NULL,100022,NULL,2,NULL,10002,10001,'2022-04-24 18:44:41','2022-05-05 09:34:49',2),(10003,'举报了帖子',NULL,NULL,100037,NULL,2,NULL,10002,10001,'2022-04-24 21:34:15','2022-05-05 09:34:42',2),(10004,'举报了帖子',NULL,NULL,100022,NULL,2,NULL,10002,10001,'2022-05-01 13:52:47','2022-05-05 09:34:34',2),(10005,'举报了帖子',NULL,NULL,100036,NULL,1,NULL,10002,NULL,'2022-05-05 09:49:18',NULL,2),(10006,NULL,'今天星期四',NULL,NULL,NULL,1,NULL,10002,NULL,'2022-05-05 12:35:22',NULL,1),(10007,NULL,'明天星期无',NULL,NULL,NULL,1,NULL,10002,NULL,'2022-05-05 12:35:45',NULL,1),(10011,NULL,'内容质量很高，继续加油，越来越好。内容质量很高，继续加油，越来越好',NULL,NULL,NULL,1,NULL,10003,NULL,'2022-05-05 12:41:23',NULL,1),(10012,'举报了帖子',NULL,NULL,100041,NULL,1,NULL,10002,NULL,'2022-05-05 22:50:54',NULL,2),(10014,'举报',NULL,NULL,100040,NULL,1,NULL,10002,NULL,'2022-05-06 11:27:34',NULL,2);
/*!40000 ALTER TABLE `advise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `categoryName` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '分类名',
  `description` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '分类描述',
  `order` tinyint(4) DEFAULT NULL COMMENT '排序',
  `icon` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '图标',
  `topicCount` int(11) DEFAULT '0' COMMENT '分类下的帖子数量',
  `status` tinyint(4) DEFAULT '1' COMMENT '1正常 2已删除',
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (100,'考研/考公','考研/考公',1,NULL,6,1),(101,'闲言趣事','闲言趣事闲言趣事',2,NULL,5,1),(102,'交流问答','艺术',3,NULL,3,1),(103,'失物招领','设计',4,NULL,2,1),(104,'表白找人','社会',5,NULL,2,1),(105,'二手信息','百科',6,NULL,3,1),(106,'校内活动','算法',7,NULL,2,1),(107,'学校公告','offer',8,NULL,2,1),(108,'招聘信息','12',9,NULL,1,1),(111,'未分类','11',10,NULL,2,1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `topicId` int(11) DEFAULT NULL COMMENT '帖子id',
  `rootId` int(11) DEFAULT NULL COMMENT '根评论id',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1正常 2已删除',
  `thumbCount` int(11) NOT NULL DEFAULT '0' COMMENT '点赞人数',
  `commentCount` int(11) NOT NULL DEFAULT '0' COMMENT '对评论回复数',
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB AUTO_INCREMENT=100129 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (100061,10002,100022,NULL,'<p>大家好，今天带来大体重减脂训练。特意找了一个体重比较大的朋友，先看下体重，他的体重是97公斤，已经6年没动了。本次训练一共包含6个训练动作，3个拉伸动作，训练每组1分钟，休息30秒，拉伸每组30秒，休息10秒钟，全程没有任何跳跃动作，强度比较小，膝盖友好，非常适合新手和大体重的朋友。</p>','2022-04-18 17:04:58',1,0,0),(100062,10002,100022,NULL,'<p>训练动作：</p><p>1.原地踏步，这个动作就是原地走路，做的时候尽量把膝盖提起来，手臂可以按自己习惯抬起来，抬得高一点低一点都可以。</p><p>2.杰克侧踏步，这个动作和开合跳很相似，做的时候只需要两个腿交替向外踏出，踏步的同时手臂举到头顶做拍手的动作。</p><p>3.分步式波比，身体自然站好，手臂伸直做直臂深蹲，深蹲到底以后俯身，手臂伸直支撑身体，然后交替把膝盖提起来在放下去，然后起身站起来。</p><p>4.对角提膝冲，身体自然站立，提起膝盖的同时，然后向内转一下手臂，用肘部去触碰提起的膝盖，同时用力去挤压腹部，然后交换对侧继续重复动作。</p><p>5.徒步爬山式，身体俯身趴在地上，手臂伸直支撑身体，然后交替踏步，做的时候收紧核心，不要出现腰部反弓的问题。</p><p>6.推举深蹲，身体自然站立，两脚间距与肩同宽，下蹲的时候伸直手臂，用手去摸地面，然后起身站起来，同时上举手臂，直到手臂伸直，做的时候注意挺直腰背，膝关节稍微外展。</p>','2022-04-18 17:05:59',1,0,0),(100063,10002,100022,NULL,'<p>拉伸动作：</p><p>1.身体侧躺在地面上，下面的腿伸直，用手臂弯曲支撑头部，弯曲上面的腿，用手拉住踝关节，出现明显拉伸感时，保持30秒。</p><p>2.身体仰卧在地面上，一条腿伸直，一条腿提起膝盖，两手拉住大腿后侧，然后尽力去正伸直大腿，大腿后侧出现明显拉伸感时，保持30秒。</p><p>3.坐在地面上，一条腿伸直，一条腿弯曲，用手去抓住直腿的脚尖，然后缓慢用力拉向自己，小腿后侧出现明显拉伸感时，保持30秒。</p>','2022-04-18 17:06:20',1,0,1),(100064,10002,100023,NULL,'<p>互联网裁员潮，可能才刚刚开始。。。</p><p>本人2021年从腾讯离职，那时候PCG人员2w+，也没有啥赚钱的产品，人员也冗余，那时候就预感不妙，管理层还计划收购搜狗，合并2000+人员。真的是把我看emo了，于是跑路。</p><p>结果最近传出消息，腾讯pcg&nbsp;+&nbsp;csig&nbsp;裁员比例在15%-30%左右。</p><p>30%是夸张了，但从朋友以及一些不靠谱的消息，pcg&nbsp;+&nbsp;csig两个事业群，15%-20%+是肯定的了。</p><p>假如不跑路，我们业务应该妥妥在裁员列表里。</p><p>讲真的之前基本不焦虑，这波大规模业务收缩，裁撤人员，让我感觉到，35岁危机？先把30岁危机度过再说吧</p>','2022-04-18 17:10:41',1,0,0),(100065,10003,NULL,100063,'这个拉伸。。。','2022-04-18 17:23:59',1,0,0),(100066,10002,100022,NULL,'<p>一起加油！</p>','2022-04-19 12:00:07',2,0,0),(100067,10002,100027,NULL,'<p>微风</p>','2022-04-19 17:25:15',2,0,0),(100068,10002,NULL,100067,'1','2022-04-19 17:25:29',2,0,0),(100069,10002,NULL,100067,'2','2022-04-19 17:25:34',2,0,0),(100070,10002,NULL,100067,'3','2022-04-19 17:25:38',2,0,0),(100071,10002,100027,NULL,'<p>微风</p>','2022-04-19 17:26:10',1,1,1),(100072,10003,100027,NULL,'<p>是的</p>','2022-04-19 17:27:51',1,0,5),(100073,10003,NULL,100071,'是的','2022-04-19 17:28:02',1,0,0),(100074,10002,NULL,100072,'嘿嘿','2022-04-19 17:29:36',1,1,0),(100075,10002,NULL,100072,'@niuqing aa','2022-04-19 17:47:41',2,0,0),(100076,10002,NULL,100072,'回复@niuqing 1','2022-04-19 17:52:20',1,0,0),(100077,10002,NULL,100072,'回复@niuqing 1111','2022-04-19 17:52:47',1,0,0),(100078,10002,NULL,100072,'回复@niuqing 2222','2022-04-19 17:52:53',1,0,0),(100079,10002,NULL,100072,'回复@niuqing 啊啊','2022-04-19 17:53:01',1,0,0),(100080,10002,100025,NULL,'<p>对</p>','2022-04-19 17:53:59',1,0,0),(100081,10003,100035,NULL,'<p>好看</p>','2022-04-20 16:13:01',1,0,0),(100082,10003,100033,NULL,'<p>很棒！！</p>','2022-04-20 16:13:42',1,0,0),(100083,10014,100035,NULL,'<p>不错哦</p>','2022-04-24 16:11:56',1,1,1),(100084,10003,100036,NULL,'<p><span style=\"color: rgb(0, 0, 0); background-color: rgb(255, 255, 255); font-size: medium; font-family: &quot;Microsoft YaHei&quot;;\">ThreadLocal很容易让人望文生义，想当然地认为是一个“本地线程”。</span></p>','2022-04-24 16:14:31',1,0,2),(100085,10002,100036,NULL,'<p>发送成功</p>','2022-04-24 16:48:23',1,0,0),(100086,10002,NULL,100084,'发送成功','2022-04-24 16:48:41',1,1,0),(100087,10002,NULL,100084,'回复@niuqing ','2022-04-24 16:49:11',2,0,0),(100088,10002,NULL,100084,'回复@niuqing：发送成功','2022-04-24 16:51:19',1,0,0),(100089,10014,100039,NULL,'<p>这是回复</p>','2022-04-24 16:54:51',2,1,0),(100090,10014,NULL,100089,'这是评论','2022-04-24 16:55:05',2,1,0),(100091,10014,NULL,100089,'回复@lisi：回复李四','2022-04-24 16:55:22',2,0,0),(100092,10022,100040,NULL,'<p>231231</p>','2022-05-01 13:50:11',1,0,1),(100093,10022,NULL,100092,'123313245','2022-05-01 13:51:16',1,0,0),(100094,10022,100022,NULL,'<p>132123</p>','2022-05-01 13:51:31',1,1,2),(100095,10002,NULL,100092,'2111','2022-05-01 13:52:13',2,0,0),(100096,10002,100036,NULL,'<p>111</p>','2022-05-06 11:10:24',2,0,0),(100097,10002,NULL,100094,'123123','2022-05-06 16:12:47',1,0,0),(100098,10002,NULL,100094,'回复@niuqing：真的吗','2022-05-06 16:13:00',1,0,0),(100099,10002,100022,NULL,'<p>6啊啊</p>','2022-05-06 16:13:11',1,0,1),(100100,10022,100022,NULL,'<p>是的</p>','2022-05-06 16:14:00',1,0,0),(100101,10022,NULL,100099,'真的呢','2022-05-06 16:14:12',2,0,0),(100102,10022,NULL,100099,'aaa','2022-05-06 16:14:23',1,0,0),(100103,10022,NULL,100099,'aaaaaad','2022-05-06 16:14:38',2,0,0),(100104,10022,NULL,100099,'44','2022-05-06 16:14:41',2,0,0),(100105,10002,100024,NULL,'<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px; font-family: system-ui, -apple-system, &quot;Segoe UI&quot;, Roboto, Ubuntu, Cantarell, &quot;Noto Sans&quot;, sans-serif;\">塞尔维亚深有体会。</span></p>','2022-05-06 18:31:14',1,0,0),(100106,10002,100041,NULL,'<p>棒棒</p>','2022-05-06 18:31:49',1,0,0),(100107,10002,100035,NULL,'<p><span style=\"color: rgb(0, 0, 0); background-color: rgb(255, 255, 255); font-size: medium; font-family: &quot;Microsoft YaHei&quot;;\">引导读者的阅读节奏</span></p>','2022-05-06 19:21:45',1,0,1),(100108,10002,NULL,100083,'嗯嗯','2022-05-06 19:21:57',1,0,0),(100109,10019,100051,NULL,'<p>白色，图书馆9楼</p>','2022-05-06 19:50:44',1,0,0),(100110,10019,100052,NULL,'<p>找到了吗</p>','2022-05-06 19:52:20',1,0,1),(100111,10015,NULL,100110,'还没','2022-05-06 19:52:40',1,0,0),(100112,10020,100059,NULL,'<p><span style=\"color: rgb(0, 0, 0); font-size: 16px; font-family: 宋体;\">在考上研究生的17位同学中，5人被东华大学录取（学术型3人），3人被苏州大学录取，2人被江南大学录取，5人被浙江理工大学录取（学术型3人），1人被武汉轻工大学录取，1人被桂林理工大学录取。班级里的一号楼605男生宿舍5人参加考研4人成功考取研究生，四号楼631女生宿舍同样战绩辉煌，4人成功上岸。</span></p>','2022-05-06 20:08:08',1,0,0),(100113,10015,100060,NULL,'<p><span style=\"color: rgb(34, 34, 34); background-color: rgb(255, 255, 255); font-size: 15px; font-family: -apple-system, BlinkMacSystemFont, &quot;Helvetica Neue&quot;, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif;\">让阅读成为一种生活习惯，将中华优秀传统文化入脑入心，沉淀血液。</span></p>','2022-05-06 20:10:55',1,0,1),(100114,10015,NULL,100107,'阅读节奏！','2022-05-06 20:12:39',1,0,0),(100115,10015,100035,NULL,'<p>赞！</p>','2022-05-06 20:12:56',1,0,1),(100116,10019,100060,NULL,'<p>多阅读，多成长</p>','2022-05-06 20:22:12',1,0,0),(100117,10019,NULL,100113,'棒棒的','2022-05-06 20:22:27',1,0,0),(100118,10019,NULL,100115,'赞！','2022-05-06 20:23:06',1,0,0),(100119,10019,100036,NULL,'<p><span style=\"color: rgb(0, 0, 0); background-color: rgb(255, 255, 255); font-size: medium; font-family: &quot;Microsoft YaHei&quot;;\">也许把它命名为ThreadLocalVariable更容易让人理解一些。</span></p>','2022-05-06 20:23:38',1,0,1),(100120,10019,NULL,100119,'变量','2022-05-06 20:23:47',1,0,0),(100121,10019,100060,NULL,'<p><span style=\"color: rgb(0, 0, 0); background-color: rgb(255, 255, 255); font-size: medium; font-family: &quot;Microsoft YaHei&quot;;\">悦读文化经典&nbsp;共赏阅读之美！</span></p>','2022-05-06 20:24:11',1,0,0),(100122,10019,100062,NULL,'<p>还行，车况有点差</p>','2022-05-06 20:25:12',1,0,0),(100123,10019,100051,NULL,'<p>联系方式1234123</p>','2022-05-06 20:25:42',1,0,0),(100124,10019,100061,NULL,'<p>价格？</p>','2022-05-06 20:26:16',1,0,0),(100125,10002,100060,NULL,'<p>书中自有黄金屋啊</p>','2022-05-06 20:27:24',1,0,0),(100126,10003,100037,NULL,'<p><span style=\"color: rgb(0, 0, 0); background-color: rgb(255, 255, 255); font-size: medium; font-family: &quot;Microsoft YaHei&quot;;\">utf8</span></p>','2022-05-06 20:29:52',1,0,0),(100127,10002,100064,NULL,'<p>回复表情?</p>','2022-05-06 21:01:34',1,0,1),(100128,10002,NULL,100127,'?','2022-05-06 21:01:41',1,0,0);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `imageId` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片id',
  `topicId` int(11) DEFAULT NULL COMMENT '帖子id',
  `commentId` int(11) DEFAULT NULL COMMENT '评论id',
  `adviseId` int(11) DEFAULT NULL COMMENT '举报和建议id',
  `path` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '图片路径',
  `uploadTime` datetime DEFAULT NULL COMMENT '上传时间',
  `uploadUserId` int(11) DEFAULT NULL COMMENT '上传者Id',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1正常 2已失效',
  PRIMARY KEY (`imageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='图片表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `notificationId` int(11) NOT NULL AUTO_INCREMENT COMMENT '通知id',
  `toUserId` int(11) NOT NULL COMMENT '目标用户id',
  `fromUserId` int(11) NOT NULL COMMENT '发起用户id',
  `subject` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '主题，通知的内容',
  `action` varchar(120) COLLATE utf8_bin DEFAULT NULL COMMENT '操作映射的地址',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 1 未读 2已读 3已删除',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `handleTime` datetime DEFAULT NULL COMMENT '处理时间',
  `topicId` int(11) DEFAULT NULL COMMENT '涉及的帖子id',
  `commentId` int(11) DEFAULT NULL COMMENT '涉及的评论id',
  PRIMARY KEY (`notificationId`)
) ENGINE=InnoDB AUTO_INCREMENT=100065 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='通知表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (100020,10002,10002,'回复了你的帖子',NULL,2,'2022-04-18 17:03:56','2022-04-18 17:24:10',100022,NULL),(100021,10002,10002,'回复了你的帖子',NULL,2,'2022-04-18 17:04:58','2022-04-18 17:24:10',100022,NULL),(100022,10002,10002,'回复了你的帖子',NULL,2,'2022-04-18 17:05:59','2022-04-18 17:24:10',100022,NULL),(100023,10002,10002,'回复了你的帖子',NULL,2,'2022-04-18 17:06:20','2022-04-18 17:24:10',100022,NULL),(100024,10002,10002,'回复了你的帖子',NULL,2,'2022-04-18 17:10:41','2022-04-18 17:24:10',100023,NULL),(100025,10002,10003,'赞了你的帖子',NULL,2,'2022-04-18 17:23:43','2022-04-18 17:24:10',100022,NULL),(100026,10002,10003,'赞了你的帖子',NULL,2,'2022-04-18 17:23:44','2022-04-18 17:24:10',100022,NULL),(100027,10002,10002,'赞了你的帖子',NULL,2,'2022-04-19 11:59:26','2022-04-19 11:59:30',100022,NULL),(100028,10002,10002,'回复了你的帖子',NULL,2,'2022-04-19 12:00:07','2022-04-19 12:00:11',100022,NULL),(100029,10002,10003,'赞了你的帖子',NULL,2,'2022-04-19 17:26:46','2022-04-19 17:27:19',100027,NULL),(100030,10002,10003,'回复了你的帖子',NULL,2,'2022-04-19 17:27:51','2022-04-19 17:29:48',100027,NULL),(100031,10003,10002,'回复了你的帖子',NULL,2,'2022-04-19 17:53:59','2022-04-19 18:10:32',100025,NULL),(100032,10002,10003,'回复了你的帖子',NULL,2,'2022-04-20 16:13:42','2022-04-20 17:22:35',100033,NULL),(100033,10002,10014,'赞了你的帖子',NULL,2,'2022-04-24 16:05:56','2022-04-24 16:08:29',100022,NULL),(100034,10003,10014,'回复了你的帖子',NULL,2,'2022-04-24 16:11:56','2022-04-24 16:15:30',100035,NULL),(100035,10014,10003,'赞了你的帖子',NULL,2,'2022-04-24 16:13:58','2022-04-24 17:11:36',100036,NULL),(100036,10014,10003,'回复了你的帖子',NULL,2,'2022-04-24 16:14:31','2022-04-24 17:11:36',100036,NULL),(100037,10014,10002,'回复了你的帖子',NULL,2,'2022-04-24 16:48:23','2022-04-24 17:11:36',100036,NULL),(100038,10002,10022,'回复了你的帖子',NULL,2,'2022-05-01 13:51:31','2022-05-01 13:51:45',100022,NULL),(100039,10022,10002,'赞了你的帖子',NULL,2,'2022-05-01 13:55:19','2022-05-01 14:06:01',100040,NULL),(100040,10014,10002,'回复了你的帖子',NULL,2,'2022-05-06 11:10:24','2022-05-06 11:56:10',100036,NULL),(100041,10002,10022,'赞了你的帖子',NULL,2,'2022-05-06 16:13:49','2022-05-06 16:15:21',100022,NULL),(100042,10002,10022,'回复了你的帖子',NULL,2,'2022-05-06 16:14:00','2022-05-06 16:15:21',100022,NULL),(100043,10003,10002,'回复了你的帖子',NULL,2,'2022-05-06 18:31:14','2022-05-06 18:33:27',100024,NULL),(100044,10001,10002,'回复了你的帖子',NULL,1,'2022-05-06 18:31:49',NULL,100041,NULL),(100045,10003,10002,'回复了你的帖子',NULL,1,'2022-05-06 19:21:45',NULL,100035,NULL),(100046,10003,10002,'赞了你的帖子',NULL,1,'2022-05-06 19:22:16',NULL,100035,NULL),(100047,10003,10002,'赞了你的帖子',NULL,1,'2022-05-06 19:22:16',NULL,100035,NULL),(100048,10003,10002,'赞了你的帖子',NULL,1,'2022-05-06 19:22:16',NULL,100035,NULL),(100049,10014,10002,'赞了你的帖子',NULL,2,'2022-05-06 19:22:20','2022-05-06 20:17:21',100036,NULL),(100050,10014,10002,'赞了你的帖子',NULL,2,'2022-05-06 19:22:24','2022-05-06 20:17:21',100036,NULL),(100051,10014,10002,'赞了你的帖子',NULL,2,'2022-05-06 19:22:25','2022-05-06 20:17:21',100036,NULL),(100052,10015,10019,'回复了你的帖子',NULL,2,'2022-05-06 19:52:20','2022-05-06 19:52:34',100052,NULL),(100053,10020,10015,'赞了你的帖子',NULL,1,'2022-05-06 20:10:06',NULL,100060,NULL),(100054,10020,10015,'回复了你的帖子',NULL,1,'2022-05-06 20:10:55',NULL,100060,NULL),(100055,10003,10015,'回复了你的帖子',NULL,1,'2022-05-06 20:12:56',NULL,100035,NULL),(100056,10020,10019,'回复了你的帖子',NULL,1,'2022-05-06 20:22:12',NULL,100060,NULL),(100057,10014,10019,'回复了你的帖子',NULL,1,'2022-05-06 20:23:38',NULL,100036,NULL),(100058,10020,10019,'回复了你的帖子',NULL,1,'2022-05-06 20:24:11',NULL,100060,NULL),(100059,10020,10019,'赞了你的帖子',NULL,1,'2022-05-06 20:24:18',NULL,100060,NULL),(100060,10014,10019,'回复了你的帖子',NULL,1,'2022-05-06 20:25:12',NULL,100062,NULL),(100061,10014,10019,'回复了你的帖子',NULL,1,'2022-05-06 20:26:16',NULL,100061,NULL),(100062,10020,10002,'赞了你的帖子',NULL,1,'2022-05-06 20:27:02',NULL,100060,NULL),(100063,10020,10002,'赞了你的帖子',NULL,1,'2022-05-06 20:27:03',NULL,100060,NULL),(100064,10020,10002,'回复了你的帖子',NULL,1,'2022-05-06 20:27:24',NULL,100060,NULL);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thumb`
--

DROP TABLE IF EXISTS `thumb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thumb` (
  `thumbId` int(11) NOT NULL AUTO_INCREMENT COMMENT '赞id',
  `fromUserId` int(11) NOT NULL COMMENT '发起用户id',
  `topicId` int(11) DEFAULT NULL COMMENT '帖子id',
  `commentId` int(11) DEFAULT NULL COMMENT '评论id',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1正常 2已取消',
  PRIMARY KEY (`thumbId`)
) ENGINE=InnoDB AUTO_INCREMENT=100121 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='点赞表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thumb`
--

LOCK TABLES `thumb` WRITE;
/*!40000 ALTER TABLE `thumb` DISABLE KEYS */;
INSERT INTO `thumb` VALUES (100071,10003,100022,NULL,'2022-04-18 17:23:44',1),(100072,10003,100022,NULL,'2022-04-18 17:23:44',1),(100073,10002,100022,NULL,'2022-04-19 11:59:26',1),(100074,10003,100027,NULL,'2022-04-19 17:26:46',1),(100075,10003,NULL,100071,'2022-04-19 17:28:26',1),(100076,10002,NULL,100074,'2022-04-19 17:33:09',1),(100077,10002,100029,NULL,'2022-04-20 10:11:00',1),(100078,10002,100029,NULL,'2022-04-20 10:11:01',1),(100079,10003,100035,NULL,'2022-04-20 16:12:30',1),(100080,10003,100035,NULL,'2022-04-20 16:12:31',1),(100081,10002,100033,NULL,'2022-04-22 10:35:52',1),(100082,10002,100033,NULL,'2022-04-22 10:35:52',1),(100083,10002,100033,NULL,'2022-04-22 10:35:53',1),(100084,10014,100022,NULL,'2022-04-24 16:05:56',1),(100085,10014,100036,NULL,'2022-04-24 16:10:55',1),(100086,10003,100036,NULL,'2022-04-24 16:13:58',1),(100087,10003,NULL,100083,'2022-04-24 16:15:39',1),(100088,10002,NULL,100086,'2022-04-24 16:48:45',1),(100089,10014,NULL,100089,'2022-04-24 16:54:57',1),(100090,10014,NULL,100090,'2022-04-24 16:55:07',1),(100091,10022,100040,NULL,'2022-05-01 13:50:07',1),(100092,10002,100040,NULL,'2022-05-01 13:55:19',1),(100093,10002,NULL,100094,'2022-05-06 16:12:34',1),(100094,10002,100022,NULL,'2022-05-06 16:13:22',1),(100095,10022,100022,NULL,'2022-05-06 16:13:49',1),(100096,10002,100022,NULL,'2022-05-06 19:22:11',1),(100097,10002,100022,NULL,'2022-05-06 19:22:12',1),(100098,10002,100022,NULL,'2022-05-06 19:22:12',1),(100099,10002,100022,NULL,'2022-05-06 19:22:13',1),(100100,10002,100022,NULL,'2022-05-06 19:22:13',1),(100101,10002,100035,NULL,'2022-05-06 19:22:16',1),(100102,10002,100035,NULL,'2022-05-06 19:22:16',1),(100103,10002,100035,NULL,'2022-05-06 19:22:16',1),(100104,10002,100036,NULL,'2022-05-06 19:22:20',1),(100105,10002,100036,NULL,'2022-05-06 19:22:24',1),(100106,10002,100036,NULL,'2022-05-06 19:22:25',1),(100107,10019,100051,NULL,'2022-05-06 19:50:28',1),(100108,10020,100060,NULL,'2022-05-06 20:09:44',1),(100109,10020,100060,NULL,'2022-05-06 20:09:44',1),(100110,10020,100060,NULL,'2022-05-06 20:09:44',1),(100111,10015,100060,NULL,'2022-05-06 20:10:06',1),(100112,10014,100062,NULL,'2022-05-06 20:17:17',1),(100113,10014,100062,NULL,'2022-05-06 20:17:17',1),(100114,10014,100062,NULL,'2022-05-06 20:17:18',1),(100115,10019,100060,NULL,'2022-05-06 20:24:18',1),(100116,10019,100051,NULL,'2022-05-06 20:24:32',1),(100117,10019,100051,NULL,'2022-05-06 20:24:33',1),(100118,10019,100051,NULL,'2022-05-06 20:24:33',1),(100119,10002,100060,NULL,'2022-05-06 20:27:02',1),(100120,10002,100060,NULL,'2022-05-06 20:27:03',1);
/*!40000 ALTER TABLE `thumb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic` (
  `topicId` int(11) NOT NULL AUTO_INCREMENT COMMENT '帖子id',
  `userId` int(11) NOT NULL COMMENT '发贴的用户id',
  `categoryId` int(11) NOT NULL COMMENT '分类id',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '内容',
  `isComment` tinyint(4) DEFAULT NULL COMMENT '是否允许评论  1允许 2不允许',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1正常 2已删除',
  `order` tinyint(4) DEFAULT NULL COMMENT '排序',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `viewCount` int(11) NOT NULL DEFAULT '0' COMMENT '浏览人数',
  `thumbCount` int(11) NOT NULL DEFAULT '0' COMMENT '点赞人数',
  `commentCount` int(11) NOT NULL DEFAULT '0' COMMENT '评论人数',
  `thumbnail` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '缩略图  主页显示的',
  PRIMARY KEY (`topicId`)
) ENGINE=InnoDB AUTO_INCREMENT=100065 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='帖子表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (100022,10002,101,'大体重者如何减肥比较合适？','<p>本人最开始是选择的慢跑，但是几天之后小腿骨头疼，走路上楼都有疼痛感。在网上看了一些教程，也试着训练了一下，发现自己无法完成整套训练。现在每天的食量减低了三分之一，远动每天30分钟，主要进行俯卧撑和卷腹，深蹲。</p><p>对于我来说这个运动量是不是太少？一个月了好似没什么效果，体重基本没变化，是不是运动量不足的原因？</p><p>说一下本人101KG，178cm.要是想到达正常体重应该怎么做？有没有快速一点的办法？</p>',NULL,1,100,'2022-04-18 17:02:04','2022-04-18 17:02:04',124,11,6,NULL),(100023,10002,102,'最近互联网大厂都在裁员？对普通程序员有什么启示？','<p>如题</p>',NULL,1,100,'2022-05-06 20:27:52','2022-04-18 17:08:33',15,0,1,NULL),(100024,10003,111,'塞尔维亚的领土完整','<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px; font-family: system-ui, -apple-system, &quot;Segoe UI&quot;, Roboto, Ubuntu, Cantarell, &quot;Noto Sans&quot;, sans-serif;\">武契奇称，如果他实施对俄制裁，就能获得想要的任何奖赏，受到所有人赞扬，立马从独夫变成民主斗士。但他不会那么做，因为实施制裁是不道德的，塞尔维亚深有体会。他强调，俄罗斯自2001年以来一直支持塞尔维亚的领土完整。</span></p>',NULL,1,100,'2022-05-06 20:29:10','2022-04-18 17:13:20',13,0,1,NULL),(100025,10003,100,'没有分类不行','<p><br></p>',NULL,2,100,'2022-04-18 17:23:07','2022-04-18 17:23:07',10,0,1,NULL),(100026,10002,102,'艺术','<p>艺术设计</p>',NULL,2,100,'2022-04-19 11:58:54','2022-04-19 11:58:54',2,0,0,NULL),(100027,10002,101,'2022年4月19日 17点23分','<p>天气转暖</p><p>温度可以</p><p><br></p>',NULL,1,100,'2022-05-06 18:30:50','2022-04-19 17:24:49',31,1,2,NULL),(100028,10003,100,'1111','<p>22222</p>',NULL,2,100,'2022-04-19 18:09:18','2022-04-19 18:09:18',2,0,0,NULL),(100029,10002,100,'niuqing发帖','<p><br></p><p>发&nbsp;&nbsp;忒</p>',NULL,2,100,'2022-04-20 10:19:14','2022-04-20 10:10:36',13,2,0,NULL),(100030,10002,105,'上传图片','<p><br></p><p><img src=\"http://localhost:8088/img/avatar/2022/04/20/a9c7f70055_aaaa.jpg\" alt=\"alt\" data-href=\"#\" style=\"width: 994.97px;height: 435.20px;\"/></p><p>图片</p><p><br></p>',NULL,2,100,'2022-04-20 15:18:35','2022-04-20 15:07:17',23,0,0,NULL),(100031,10002,100,'111','<p><img src=\"http://localhost:8088/img/avatar/2022/04/20/b9f4c29af0_abc.png\" alt=\"alt\" data-href=\"#\" style=\"\"/></p>',NULL,2,100,'2022-04-20 15:20:52','2022-04-20 15:20:52',2,0,0,NULL),(100032,10002,100,'111','<p><img src=\"http://localhost:8088/img/avatar/2022/04/20/511d55c5fa_aaaa.jpg\" alt=\"alt\" data-href=\"#\" style=\"\"/></p>',NULL,2,100,'2022-04-20 15:21:26','2022-04-20 15:21:26',30,0,0,NULL),(100033,10002,101,'aaa','<p><img src=\"http://localhost:8088/img/avatar/2022/04/20/589bb9720e_abc.png\" alt=\"alt\" data-href=\"#\" style=\"\"/></p>',NULL,2,100,'2022-04-20 15:47:35','2022-04-20 15:47:35',6,3,1,NULL),(100034,10002,102,'yi','<p>yihang</p><p>liangg</p><p>san</p><p><img src=\"http://localhost:8088/img/avatar/2022/04/20/3a23f72471_douyin.png\" alt=\"alt\" data-href=\"#\" style=\"\"/></p>',NULL,2,100,'2022-04-20 16:02:28','2022-04-20 16:01:54',8,0,0,NULL),(100035,10003,101,'图片分享 2022-04-20','<p>一个吸引眼球的图片可以大大提升设计品的被关注度，而一张在合适位置出现的图片可以潜意识地引导读者的阅读节奏，带来意犹未尽的观赏效果。</p><p>以下是本人总结出的7个超好用的高清图片，希望可以帮到大家。</p><p><br></p><p><img src=\"https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/e0f509216e_571e_r.jpg\" alt=\"alt\" data-href=\"#\" style=\"width: 30%;\"/></p>',NULL,1,100,'2022-05-06 18:34:11','2022-04-20 16:12:05',22,5,4,NULL),(100036,10014,111,'ThreadLocal是Thread的局部变量，用于编多线程程序，对解决多线程程序的并发问题有一定的启示作用。','<p>线程程序介绍</p><p>早在JDK&nbsp;1.2的版本中就提供java.lang.ThreadLocal，ThreadLocal为解决多线程程序的并发问题提供了一种新的思路。使用这个工具类可以很简洁地编写出优美的多线程程序。</p><p>关于其变量</p><p>ThreadLocal很容易让人望文生义，想当然地认为是一个“本地线程”。其实，ThreadLocal并不是一个Thread，而是Thread的局部变量，也许把它命名为ThreadLocalVariable更容易让人理解一些。</p><p>所以，在Java中编写线程局部变量的代码相对来说要笨拙一些，因此造成线程局部变量没有在Java开发者中得到很好的普及。</p><p>ThreadLocal的接口方法</p><p>ThreadLocal类接口很简单，只有4个方法，我们先来了解一下：</p><p>void&nbsp;set(Object&nbsp;value)</p><p>public&nbsp;void&nbsp;remove()</p><p><img src=\"http://localhost:8088/img/avatar/2022/04/24/ada266342d_image.png\" alt=\"alt\" data-href=\"#\" style=\"\"/></p><p>将当前线程局部变量的值删除，目的是为了减少内存的占用，该方法是JDK&nbsp;1.5&nbsp;新增的方法。需要指出的是，当线程结束后，对应该线程的局部变量将自动被垃圾回收，所以显式调用该方法清除线程的局部变量并不是必须的操作，但它可以加快内存回收的速度。</p><p><br></p><p><br></p>',NULL,1,100,'2022-05-06 20:13:40','2022-04-24 16:10:49',33,5,3,NULL),(100037,10003,111,'mysql设置默认字符','<p>打开my.cnf后，在文件内的[mysqld]下增加如下设置并重启服务:</p><p>character_set_server=utf8&nbsp;&nbsp;</p><p>如果有client在文件内[client]末尾增加</p><p>default-character-set=utf8</p>',NULL,1,100,'2022-05-06 20:29:43','2022-04-24 16:29:25',9,0,1,NULL),(100038,10002,100,'mysql设置时区','<p>#vim&nbsp;/etc/my.cnf&nbsp;</p><p>添加default-time_zone&nbsp;=&nbsp;\'+8:00\'</p><p>#systemctl&nbsp;restart&nbsp;mysql</p><p>查看时区</p><p>show&nbsp;variables&nbsp;like&nbsp;\"%time_zone%\";</p><p>select&nbsp;now();</p><p><br></p>',NULL,2,100,'2022-04-24 16:36:22','2022-04-24 16:35:53',4,0,0,NULL),(100039,10014,100,'这是标题','<p><br></p><p>这是内容</p>',NULL,2,100,'2022-04-24 16:54:41','2022-04-24 16:54:15',4,0,0,NULL),(100040,10022,102,'123456','<p><br></p><p>ihiuhh</p><p><img src=\"http://localhost:8088/img/avatar/2022/05/01/1b68347f92_a382_r.jpg\" alt=\"alt\" data-href=\"#\" style=\"width: 506.00px;height: 506.00px;\"/></p><p><br></p><p><br></p><p>21111</p><p><br></p>',NULL,2,100,'2022-05-01 14:02:28','2022-05-01 13:49:57',14,2,1,NULL),(100041,10001,101,'管理员写的帖子！！！','<p>管理员写的帖子！！！管理员写的帖子！！！</p>',NULL,1,100,'2022-05-03 23:21:00','2022-05-03 23:21:00',6,0,1,NULL),(100042,10002,100,'1231231','<p>头像棒棒</p><p>好看`</p><p><br></p><p><img src=\"https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/c3b6eafa08_01f6_r.jpg\" alt=\"alt\" data-href=\"#\" style=\"width: 271.30px;height: 271.30px;\"/></p>',NULL,2,100,'2022-05-06 11:55:26','2022-05-06 10:48:10',6,0,0,NULL),(100043,10001,100,'niuniuuu','<p><img src=\"https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/98c7252f1c_126e_r.jpg\" alt=\"alt\" data-href=\"#\" style=\"\"/></p>',NULL,2,100,'2022-05-06 10:53:53','2022-05-06 10:53:53',3,0,0,NULL),(100044,10021,100,'考研，直系专业学长学姐1对1帮忙（包你一战上岸）','<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px; font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif;\">学长学姐帮忙的范围包括：提供专业报录比信息，划定复习重点，提供参考资料，公共课和专业课复习问题解答，牵线搭桥联系导师，考试技巧训练…………使你一站到岸啰！</span></p>',NULL,1,100,'2022-05-06 19:37:18','2022-05-06 19:37:18',0,0,0,NULL),(100045,10017,100,'家人们可以进来听我倒倒垃圾吗求求了','<p>我是医学考研生，二月份就开始备考了，下个月就下实习点实习了，二三月的时候靠着心中一股很想上岸的劲头，天天学累了就翻目标院校的录取分数，效率也很高。到这段时间就觉得好孤独啊我焯，那股上岸的劲头没那么强烈了，每天也都在按部就班复习，但是总是会胡思乱想，没有一个精神支柱感觉…甚至想前女友（上学期我提分的，觉得太不会照顾人了，根本没有恋爱的感觉，现在觉得至少有个人和你聊聊天啥的觉得有比没有强？），不知道是不是矫情，或者怎么排解这种孤独感啊，找研友线下不太现实马上要下点了，线上有和没有差不多？每天也能找到能说说笑笑的人，但都不是能谈心的，每个人复习的进度不一样，甚至有的人跨考的，白天还好，天一黑负面情绪、孤独感就涌上来了，是我一个人这种情况吗，有没有家人说说怎么办</p><p><br></p>',NULL,1,100,'2022-05-06 19:38:16','2022-05-06 19:38:16',0,0,0,NULL),(100046,10021,100,'xdm，最近开始数一强化了','<p>做的张宇一千题，做完九章了，除去证明题正确率80左右，今年数学有望130吗</p><p><br></p>',NULL,1,100,'2022-05-06 19:39:13','2022-05-06 19:39:13',0,0,0,NULL),(100047,10017,100,'分享一下一个疯狂想辞职的县直部门公务员的想法','<p>先说一下待遇吧，西北某18线小县城，到手5000，一年6W，没绩效，没年终，基本当地财政就靠上级拨款活着，此为背景。</p><p>自上班3个月以来晚上能10点以前下班的日子屈指可数，周末、中秋什么的全正常上班，包括十一假期也告诉我们提前占用了。基本上我认知的单位一般都挺保障手下人的利益的，但这单位是以加班为荣，恨不得你把单位当成家一样，不过没有单位食堂，没有宿舍，我刚来的时候连饮水机都没有，想喝水自带。</p><p>就感觉当地政府就像搭建的草台班子，当地人口也就一两万，财政局的账面都是赤字，还经常想一出是一出，只知道折腾手底下的人，想不通同一个市的临县为何差距这么大。</p><p><img src=\"https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/9cbed61679_dde7_r.jpg\" alt=\"alt\" data-href=\"#\" style=\"width: 30%;\"/></p>',NULL,1,100,'2022-05-06 19:40:34','2022-05-06 19:40:34',0,0,0,NULL),(100048,10021,101,'手抓饼的故事，大家帮我分析一下','<p>我以前高中时候门口的一家手抓饼特别好吃（老板是个大叔），后面毕业了&nbsp;老板换成了他老婆，后面每次去买的时候，他第一句就是儿子来点啥，儿子要不要辣椒，反正就是对10多20岁大都叫儿子。我觉得这是一个很不礼貌的称呼（甚至感到特别不爽）。大家咋看。是不是我在太矫情了，反正就是感觉很不爽就跟出来买个吃的还得认个妈一样。</p>',NULL,1,100,'2022-05-06 19:42:56','2022-05-06 19:42:56',0,0,0,NULL),(100049,10021,102,'亚奥理事会决定杭州亚运会延期举行，将带来哪些影响？','<p>亚洲奥林匹克理事会总干事6日宣布，原定于2022年9月10日至25日在中国杭州举办的第19届亚洲运动会将延期举行，具体日期择期宣布。（央视新闻）</p>',NULL,1,100,'2022-05-06 19:45:49','2022-05-06 19:45:49',0,0,0,NULL),(100050,10015,102,'你读的大学有哪些校园风景?','<p>转眼间大学生活都快过完了，很怀念这里的一草一木以及遇到的朋友，最近又拍了几张校园的景色觉得很美，母校是我们每个人最好的学校，它是我们曾经学习生活过的地方，是我们校园生活回忆的背景。</p><p>接下来介绍一下我所在大学的风景。首先映入眼帘的是图书馆</p>',NULL,1,100,'2022-05-06 19:46:20','2022-05-06 19:46:20',0,0,0,NULL),(100051,10019,103,'耳机，苹果耳机一代','<p>现3.28日下午18:20在图书馆桌子上捡到一个iPhone&nbsp;1代耳机，希望看到是你的东西可以找我拿回！</p><p>如下图</p><p><img src=\"https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/a81069affc_image.png\" alt=\"alt\" data-href=\"#\" style=\"width: 30%;\"/></p>',NULL,1,100,'2022-05-06 20:21:24','2022-05-06 19:49:23',17,4,2,NULL),(100052,10015,103,'失物招领，找电脑','<p>5.3号下午7点左右在教学楼附近，丢失一台笔记本电脑，玫瑰金色，电脑包灰黑色，里面有一个黑色的电脑支架，一个粉白色鼠标，请捡到的好心人联系我</p>',NULL,1,100,'2022-05-06 19:52:08','2022-05-06 19:52:08',4,0,1,NULL),(100053,10019,105,'出书，吧友友情价','<p>图如下</p><p>出书，吧友友情价</p><p>CAD、C语言</p><p><img src=\"https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/2957d436f4_image.png\" alt=\"alt\" data-href=\"#\" style=\"width: 50%;\"/></p>',NULL,1,100,'2022-05-06 19:56:04','2022-05-06 19:54:53',7,0,0,NULL),(100054,10015,102,'四级频率是多少啊','<p>请问河南工程学院南区四级频率多少啊</p>',NULL,1,100,'2022-05-06 19:58:40','2022-05-06 19:58:40',0,0,0,NULL),(100055,10015,106,'【2021年招新】外语广播电台','<p>哇哦，好激动，又有好多学弟学妹要来我们河南工程学院啦！哈喽，大家好！我们是河南河南工程学院外语广播电台(Henan&nbsp;institute&nbsp;of&nbsp;engineering&nbsp;foreign&nbsp;language&nbsp;broadcasting&nbsp;station)，欢迎大家加入这个大家庭哦!</p>',NULL,1,100,'2022-05-06 20:00:25','2022-05-06 20:00:25',0,0,0,NULL),(100056,10001,107,'2022年全国大学生数学建模竞赛报名通知','<p>全国大学生数学建模竞赛是国家教育部高教司和中国工业与应用数学学会共同主办的面向全国大学生的群众性科技活动，目的在于激励学生学习数学的积极性，提高学生建立数学模型和运用计算机技术解决实际问题的综合能力，鼓励广大学生踊跃参加课外科技活动，开拓知识面，培养创造精神及合作意识。我校自2008年参加高教杯全国大学生数学建模本科组竞赛以来，不断取得优异的成绩，共获国家一等奖7组，国家二等奖69组，河南省一等奖230组，省一等奖以上获奖率为74%，获奖数量、质量均居河南省高校前列。</p><p>为了加强我校学生的创新与实践能力，2022年全国大学生数学建模竞赛报名工作已正式开始，欢迎有兴趣的同学积极报名参与。</p>',NULL,1,100,'2022-05-06 20:02:40','2022-05-06 20:02:40',0,0,0,NULL),(100057,10001,107,'关于组织观看电影《我要去远方》的通知','<p>各基层党委、党总支：</p><p>为全面贯彻落实习近平总书记防灾减灾救灾重要论述，深入践行社会主义核心价值观，弘扬防震减灾文化，加强防震减灾科普宣传教育，在中国地震局、北京市委宣传部和云南省委宣传部的指导支持下，由北京市地震局牵头，联合云南、浙江、上海等省市地震部门和有关单位共同创作的地震科普教育宣传题材电影《我要去远方》巳在全国公映。现将有关观影工作事项通知如下：</p><p>一、电影基本情况。影片以讲好“地震人”的故事为主线，真实展现了基层地震台站地震工作者的工作和生活，充分彰显了“开拓创新，求真务实，攻坚克难，坚守奉献”的防震减灾行业精神以及保障人民群众生命财产安全的初心和使命。</p><p>二、做好观影组织工作。各单位要精心组织、科学安排，结合主题党日活动，在落实疫情防控相关要求前提下，做好广大师生观影组织工作，通过观看影片，感悟防震减灾行业精神，激励党员干部新时代新担当新作为，以实际行动践行初心使命。</p>',NULL,1,100,'2022-05-06 20:03:53','2022-05-06 20:03:53',0,0,0,NULL),(100058,10015,108,'公司招聘司机','<p>我公司直招网约车司机</p><p>月工资净剩8000-10000</p><p>可上班&nbsp;周付&nbsp;月付---押金低租金低</p><p>方式灵活&nbsp;多跑多得&nbsp;工作时间自由不限制&nbsp;车辆维修保养救援公司负责</p><p>优先派单&nbsp;保障单量</p>',NULL,1,100,'2022-05-06 20:06:05','2022-05-06 20:06:05',0,0,0,NULL),(100059,10020,100,'河工榜样 汇聚向上向善正能量——2022届毕业生考研明星班（一）','<p>星光不问赶路人，时光不负有心人。在2022年全国硕士研究生招生考试中，河南工程学院化工与印染工程学院轻化工程1842班取得了耀眼的成绩，班上38名同学中，有17名同学成功考取研究生，班级考研录取率达到44.7%。</p>',NULL,1,100,'2022-05-06 20:07:39','2022-05-06 20:07:39',2,0,1,NULL),(100060,10020,111,'四月芳菲好，正是读书时！河南工程学院举行校园读书节','<p>人间四月芳菲尽，明月湖畔书香醉。阅读经典凝春色，润泽书香最致远！为庆祝第27个世界读书日，进一步推进书香校园建设，营造良好的文化氛围，4月23日下午，以“悦读文化经典&nbsp;共赏阅读之美”为主题的“明月湖”读书节活动在河南工程学院龙湖西校区明月湖畔拉开了序幕。</p><p><img src=\"https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/591b1b2512_image.png\" alt=\"alt\" data-href=\"#\" style=\"width: 50%;\"/></p>',NULL,1,100,'2022-05-06 20:09:35','2022-05-06 20:09:35',7,7,4,NULL),(100061,10014,105,'出一台游戏本。','<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px; font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif;\">出一台游戏本。【闲鱼】「我在闲鱼发布了【华硕天选二锐龙处理器+3050TI显卡，京东卖6599，买了】</span></p>',NULL,1,100,'2022-05-06 20:18:11','2022-05-06 20:15:12',4,0,1,NULL),(100062,10014,105,'出一辆电车 600 车况很破很破 这坏那坏 车很可以骑，一周左右 ，活动范围柳荷 偶尔松园快递','<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px; font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif;\">出一辆电车&nbsp;</span><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px; font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif;\"><strong>600</strong></span></p><p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px; font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif;\">车况很破很破&nbsp;这坏那坏车很可以骑，一周左右&nbsp;，活动范围西区南区快递</span></p><p><br></p><p><img src=\"https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/b741fddd7e_image.png\" alt=\"alt\" data-href=\"#\" style=\"\"/></p><p><br></p>',NULL,1,100,'2022-05-06 20:17:03','2022-05-06 20:17:03',2,3,1,NULL),(100063,10002,111,'aaa','<p>1111</p>',NULL,2,100,'2022-05-06 20:56:25','2022-05-06 20:56:25',2,0,0,NULL),(100064,10002,111,'可以发表情啦?','<p>???</p>',NULL,1,100,'2022-05-06 21:00:48','2022-05-06 20:59:09',10,0,1,NULL);
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `stuNo` bigint(20) DEFAULT NULL COMMENT '学号',
  `sex` varchar(4) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `city` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '城市',
  `realname` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '真实姓名',
  `phone` bigint(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '电子邮箱',
  `avatar` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '头像地址',
  `describe` varchar(240) COLLATE utf8_bin DEFAULT NULL COMMENT '简介、描述',
  `registerTime` datetime NOT NULL COMMENT '注册时间',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '登录时间',
  `lastLoginIp` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '登录ip',
  `role` tinyint(4) NOT NULL DEFAULT '1' COMMENT '身份1学生 2教职工 3管理员',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态1正常 2冻结 3已注销',
  `topicCount` int(11) NOT NULL DEFAULT '0' COMMENT '用户发表过的帖子数量',
  `commentCount` int(11) NOT NULL DEFAULT '0' COMMENT '用户发表过的评论数量',
  `thumbCount` int(11) NOT NULL DEFAULT '0' COMMENT '收到的赞',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10033 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (10001,'niuniu','123456',202050915310,'男','1997-11-11 00:00:00',NULL,'牛牛',18272465256,'april_niuniu@163.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/c53db88e61_126e_r.jpg','当到达一个新的环境后,我们有必要对自己进行适当的介绍,自我介绍是一种认识自我的手段。那么你真的会写自我介绍吗?','1997-11-11 00:00:00','2022-05-06 20:30:26',NULL,3,1,3,0,0),(10002,'niuqing','123456',202050915309,'男','1997-11-11 00:00:00','杭州','牛庆',17513176686,'1446002@qq.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/0ce4401876_6fd6_r.jpg','好好学习天天向上。','1997-11-11 00:00:00','2022-05-06 20:52:23',NULL,1,1,4,24,0),(10003,'zhangsan','123456',201850915308,'男','1998-03-15 00:00:00','郑州','Tom',12412351231,'1393628693@qq.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/c53db88e61_126e_r.jpg','我是张三','2022-03-29 17:23:19','2022-05-06 20:28:56',NULL,1,1,3,7,0),(10014,'lisi','123456',202050915307,'女','1998-06-25 00:00:00','杭州','李四',13853269482,'1446@qq.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/9ccab434c1_aa31_r.jpg','这是链接666','2022-04-02 17:23:19','2022-05-06 20:13:23',NULL,1,1,3,1,0),(10015,'wangwu','123456',202050915420,'男','2001-03-12 00:00:00','周口','王五',13548946231,'abca@qq.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/fe48689f51_21a8_r.jpg','Hello World!','2022-04-26 14:51:41','2022-05-06 19:41:03',NULL,1,1,5,4,0),(10016,'wangmazi','123456',202050915433,'男','2000-02-16 00:00:00','郑州','王麻子',148946513123,'66685@qq.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/c53db88e61_126e_r.jpg',NULL,'2022-04-26 14:54:45','2022-04-26 14:54:45',NULL,1,1,0,0,0),(10017,'libai','123456',202050915410,'男','2000-10-19 00:00:00','郑州','李白',123456789090,'libai@qq.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/4608582307_d9fb_r.jpg',NULL,'2022-04-26 15:12:08','2022-05-06 19:24:12',NULL,1,1,2,0,0),(10018,'tom','123456',202050915411,'女',NULL,'郑州','tom',18974561231,'tom@163.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/c53db88e61_126e_r.jpg',NULL,'2022-04-29 09:57:13','2022-04-29 09:57:13',NULL,1,1,0,0,0),(10019,'liqingzhao','123456',202050915413,'女','1935-03-19 00:00:00','郑州','李清照',15212349874,'liqingzhao@qq.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/541c1c6bd8_c2ee_r.jpg',NULL,'2022-04-29 09:59:56','2022-05-06 20:21:06',NULL,1,1,2,11,0),(10020,'jerry','123456',202050915466,'女','2006-04-19 00:00:00','郑州','杰瑞',1886952484,'1884965@163.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/c53db88e61_126e_r.jpg',NULL,'2022-04-29 10:02:39','2022-05-06 20:07:14',NULL,1,1,2,1,0),(10021,'jack','123456',201915201561,'男',NULL,'郑州','杰克',16548946511,'6166@qq.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/8fd223bbca_1131_r.jpg',NULL,'2022-04-29 10:03:29','2022-05-06 19:35:00',NULL,1,1,4,0,0),(10022,'lipengwei','123456',202050916456,'男','2001-05-13 00:00:00','郑州','李大牛',NULL,'lpw_15565365491@163.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/1350f4068f_aafc_r.jpg','I am LPW','2022-05-01 13:46:26','2022-05-06 18:04:33',NULL,1,1,0,5,0),(10023,'niuqing1','123456',202050916451,'男','1990-01-30 00:00:00','郑州','李二牛',NULL,'124435@qq.com',NULL,NULL,'2022-05-05 18:40:41','2022-05-05 18:40:41',NULL,1,1,0,0,0),(10024,'apple','123456',202050916421,'女','2001-05-13 00:00:00','郑州','牛牛',13845829647,'537454546@qq.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/fee7c3c441_571e_r.jpg',NULL,'2022-05-06 18:19:07','2022-05-06 18:19:07',NULL,1,1,0,0,0),(10025,'songjiang','123456',202050916422,'女','2001-05-13 00:00:00','郑州','孤勇者',17845613658,'78634545@qq.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/df0c107faa_a382_r.jpg',NULL,'2022-05-06 18:19:56','2022-05-06 18:19:56',NULL,1,1,0,0,0),(10026,'chaogai','123456',202050916423,'女','2001-05-13 00:00:00','郑州','孤勇者',13845614876,'14542753357@qq.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/ab60eeab2a_a382_r.jpg',NULL,'2022-05-06 18:20:48','2022-05-06 18:20:48',NULL,1,1,0,0,0),(10027,'13845614876','123456',202050916424,'男','2001-05-13 00:00:00','郑州','风吹',13845614876,'1573453423@qq.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/675814bf83_aafc_r.jpg',NULL,'2022-05-06 18:21:03','2022-05-06 18:21:03',NULL,1,1,0,0,0),(10028,'cater','123456',202050916425,'男','2001-05-13 00:00:00','郑州','凯特',13845614232,'14543453@qq.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/f97a5ab734_dde7_r.jpg',NULL,'2022-05-06 18:21:48','2022-05-06 18:21:48',NULL,1,1,0,0,0),(10029,'caterq','123456',202050916426,'男','2001-05-13 00:00:00','郑州','风',13845614232,'135487545@qq.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/f97a5ab734_dde7_r.jpg',NULL,'2022-05-06 18:21:48','2022-05-06 18:21:48',NULL,1,1,0,0,0),(10030,'1903231','123456',202050916427,'男','2001-05-13 00:00:00','郑州','333',13845614232,'1437886786@qq.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/f97a5ab734_dde7_r.jpg',NULL,'2022-05-06 18:21:48','2022-05-06 18:21:48',NULL,1,1,0,0,0),(10031,'jack123','123456',202050916431,'男','2001-05-13 00:00:00','郑州',NULL,13845614232,'572546378@qq.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/f97a5ab734_dde7_r.jpg',NULL,'2022-05-06 18:21:48','2022-05-06 18:21:48',NULL,1,1,0,0,0),(10032,'wang333','123456',202050916433,'女','2001-05-13 00:00:00','郑州',NULL,13845614232,'777845383@qq.com','https://hcs-img.oss-cn-beijing.aliyuncs.com/2022/05/06/f97a5ab734_dde7_r.jpg',NULL,'2022-05-06 18:21:48','2022-05-06 18:21:48',NULL,1,1,0,0,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-06 21:02:50
