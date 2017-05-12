-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version    5.5.15


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema chunks
--

CREATE DATABASE IF NOT EXISTS chunks;
USE chunks;

--
-- Definition of table `article_master`
--

DROP TABLE IF EXISTS `article_master`;
CREATE TABLE `article_master` (
  `articleId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `articleName` varchar(45) NOT NULL,
  `articleDesc` longtext,
  `isActive` varchar(3) DEFAULT NULL,
  `modifiedDate` datetime NOT NULL,
  `createdDate` datetime NOT NULL,
  `categoryId` int(10) unsigned DEFAULT NULL,
  `isFeature` varchar(3) DEFAULT NULL,
  `articleLocation` varchar(128) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `articleTitle` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`articleId`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `article_master`
--

/*!40000 ALTER TABLE `article_master` DISABLE KEYS */;
INSERT INTO `article_master` (`articleId`,`articleName`,`articleDesc`,`isActive`,`modifiedDate`,`createdDate`,`categoryId`,`isFeature`,`articleLocation`,`status`,`articleTitle`) VALUES
 (1,'My Doc','<p>Prior to Android 3.0, the long press gesture (a touch that&rsquo;s held in the same position for a moment) was used to display contextual actions for a given data item in a contextual menu. This pattern changed with Android 3.0. The long press gesture is now used to select data, combining contextual actions and selection management functions for selected data into a new element called the contextual action bar (CAB).</p><p><strong>Reference:</strong><a href=\'http://developer.android.com/design/patterns/selection.html\' target=\'_blank\'>http://developer.android.com/design/patterns/selection.html</a></p><h2><span id=\'What_is_CAB\'>What is CAB?</span></h2><p>The selection CAB is a temporary action bar that overlays your app&rsquo;s current action bar while data is selected. It appears after the user long presses on a selectable data item. Select additional data items by touching them. It is recommended to use when you perform actions with selected data like plain text or data items from ListView or GridView components. <strong>The action mode is disabled and the contextual action bar disappears when the user deselects all items, presses the BACK button, or selects the checkmark button on the left side of the bar.</strong></p><h2><span id=\'Implementing_CAB_for_ListView_item_selection\'>Implementing CAB for ListView item selection</span></h2> <ul> <li>If you&rsquo;re using Android 3.0 or higher there is a <a href=\'http://developer.android.com/guide/topics/ui/menus.html#CAB\' target=\'_blank\'>detailed description of CAB setup on each selection method</a> &ndash; whether contextual action should be performed on a single selected item or on a group of selected items.</li> <li>Although it is available from Android 3.0 it is a good practice to use it in apps working on earlier API versions. If your app works on android version 2.x and higher the best approach is to use <span class=\'text-highlight\'>ActionBarSherlock</span> library. It has its own contextual action bar implementation, which is easy to set up. But it doesn&rsquo;t support the native ListView integration, so you need to control CAB&rsquo;s lifecycle by yourself.</li> </ul> <p>You can invoke contextual action mode based on one of the two events or both.</p> <ol> <li>The user selects a checkbox or similar UI component within the view.</li> <li>The user performs a long-click on the view.</li> </ol> <p>This tutorial explains the second method.</p> <p><img src=\'http://theopentutorials.com/wp-content/uploads/android-contextual-action-bar-with-listview-long-press_3694/android_contextual_actionbar_listview_example.png?5b264b\' alt=\'action bar\' width=\'421\' height=\'285\' /></p> <p>&nbsp;</p> <p>In this tutorial we&rsquo;ll see how to use <strong>Contextual Action Bar for ListView using ActionBarSherlock library</strong> where list items can be selected on <strong>long press gesture</strong>. Here, contextual action bar has <strong>delete menu item</strong>, which <strong>deletes the selected items from ListView</strong>.</p>','yes','2013-08-12 00:00:00','2013-08-12 00:00:00',1,'no','D:\\Workspace\\MyChunks\\uploaded\\Abrar_Khan_Resume_-_Production.doc','Pending','My New Doc'),
 (2,'this is my test doc','<!DOCTYPE html>\r\n<html>\r\n<head>\r\n</head>\r\n<body>\r\nthis is my test doc\r\n\r\n[b]this is my test doc[/b]\r\n\r\nthis is my test doc\r\n\r\n[img]../UploadServlet?getthumb=2.jpg[/img]\r\n</body>\r\n</html>','yes','2013-09-11 00:00:00','2013-09-11 00:00:00',NULL,'yes',NULL,NULL,'this is my test doc'),
 (3,'My Doc','&lt;!DOCTYPE html&gt;\r\n&lt;html&gt;\r\n&lt;head&gt;\r\n&lt;/head&gt;\r\n&lt;body&gt;\r\norg.apache.commons.lang3.StringEscapeUtils.\r\n\r\nerjewrjer\r\n\r\ndfer\r\n\r\n[img]UploadServlet?getthumb=1.jpeg[/img]\r\n\r\nThis is my test image\r\n&lt;/body&gt;\r\n&lt;/html&gt;','yes','2013-08-24 00:00:00','2013-08-24 00:00:00',NULL,'yes',NULL,NULL,'My New Doc'),
 (4,'My Doc','\r\n\r\n\r\n\r\n\r\nthis is testing\r\n\r\n[url=http://www.google.com]http://www.google.com[/url]\r\n\r\n[img]../UploadServlet?getthumb=1.jpeg[/img]\r\n\r\ntedfjewjrewd\r\n\r\n','yes','2013-09-24 00:00:00','2013-09-24 00:00:00',NULL,'yes',NULL,NULL,'My New Doc'),
 (5,'my first look','&lt;p&gt;&lt;br /&gt;&lt;a href=&quot;http://www.google.com&quot;&gt;www.google.com&lt;/a&gt;&lt;/p&gt;\r\n&lt;p&gt;&lt;span style=&quot;background-color: #ff0000; color: #0000ff;&quot;&gt;This is for your color&lt;/span&gt; &lt;img src=&quot;../../js/tinymce/plugins/emoticons/img/smiley-cry.gif&quot; alt=&quot;&quot; /&gt;&lt;br /&gt;&lt;br /&gt;&lt;img src=&quot;../../UploadServlet?getthumb=1.jpeg&quot; alt=&quot;test&quot; width=&quot;75&quot; height=&quot;33&quot; /&gt;&lt;br /&gt; &lt;br /&gt;&lt;br /&gt;saererfvsdf&lt;/p&gt;','yes','2013-08-24 00:00:00','2013-08-24 00:00:00',NULL,'yes',NULL,NULL,'my first look'),
 (10,'test','&lt;p&gt;test&lt;/p&gt;','yes','2013-08-24 00:00:00','2013-08-24 00:00:00',NULL,NULL,NULL,NULL,'test'),
 (11,'Akash First Article on Java','&lt;h2&gt;&lt;strong&gt;Akash First Article on Java Title&lt;/strong&gt;&lt;/h2&gt;\r\n&lt;p&gt;&lt;img src=&quot;../UploadServlet?getthumb=2.jpg&quot; alt=&quot;my test diagram&quot; width=&quot;98&quot; height=&quot;65&quot; /&gt;&lt;/p&gt;\r\n&lt;p&gt;This image shows how to set up java application.&lt;/p&gt;\r\n&lt;p&gt;Below code use for the same purpose.&lt;/p&gt;','yes','2013-08-26 00:00:00','2013-08-26 00:00:00',NULL,NULL,NULL,NULL,'Akash First Article on Java Title'),
 (12,'article posted for core java','&lt;p&gt;My COre Java Article posted on 27 August&lt;/p&gt;\r\n&lt;p&gt;&lt;a href=&quot;../../../DownloadServlet?fileName=test.zip&quot;&gt;download test&lt;/a&gt;&lt;/p&gt;\r\n&lt;p&gt;&amp;nbsp;&lt;/p&gt;','yes','2013-09-04 17:42:20','2013-08-27 12:59:25',1,'yes',NULL,'Approved','Core Java Article'),
 (13,'test','&lt;p&gt;dfarvcv aefrf svcvdsrewcvdfd fdsfd &lt;img src=&quot;UploadServlet?getthumb=1.jpeg&quot; alt=&quot;dsfdfre &quot; width=&quot;75&quot; height=&quot;33&quot; /&gt;&lt;/p&gt;','yes','2013-08-28 13:52:50','2013-08-28 13:52:50',NULL,NULL,NULL,NULL,'afewr'),
 (14,'test this article','&lt;p&gt;test this article&lt;/p&gt;','yes','2013-08-28 14:12:02','2013-08-28 14:12:02',NULL,NULL,NULL,NULL,'test this article'),
 (15,'123My Doc','&lt;p&gt;123asdrv afasr afdare&lt;/p&gt;','no','2013-08-28 15:53:50','2013-08-28 15:53:50',2,'no',NULL,'Pending','123test'),
 (16,'432','&lt;p&gt;ewqew2312321323&lt;/p&gt;','no','2013-08-28 15:57:54','2013-08-28 15:57:54',2,'no',NULL,'Pending','2323'),
 (17,'for testing','&lt;p&gt;this one is for testing purpose only....&lt;/p&gt;','no','2013-08-28 16:45:48','2013-08-28 16:45:48',3,'no',NULL,'Pending','for testing'),
 (18,'MY First Google Image Article','&lt;p&gt;&lt;img src=&quot;https://lh4.googleusercontent.com/-iUa2wwdk_RA/UiciVskqWNI/AAAAAAAAAC0/Uybe8HDeyhQ/title.jpg&quot; alt=&quot;test&quot; width=&quot;204&quot; height=&quot;136&quot; /&gt;&lt;/p&gt;\r\n&lt;p&gt;This is test for google images&lt;/p&gt;\r\n&lt;p&gt;&amp;nbsp;&lt;/p&gt;\r\n&lt;p&gt;Again posting the second image from google too&lt;/p&gt;\r\n&lt;p&gt;&lt;img src=&quot;https://lh3.googleusercontent.com/-RZ3Wxpl5erU/UiciVY66zyI/AAAAAAAAACw/3xKk_ncwAmE/title.jpg&quot; alt=&quot;second Image&quot; width=&quot;278&quot; height=&quot;121&quot; /&gt;&lt;/p&gt;','no','2013-09-04 17:45:42','2013-09-04 17:45:42',1,'no',NULL,'Pending','MY First Google Image Article'),
 (19,'this is syntax highligher','&lt;p&gt;this is syntaxHighler,&lt;/p&gt;\r\n&lt;p&gt;&amp;nbsp;&lt;/p&gt;\r\n&lt;p&gt;&amp;lt;div class=&quot;dp-highlighter&quot;&amp;gt;&amp;lt;div class=&quot;bar&quot;&amp;gt;&amp;lt;div class=&quot;tools&quot;&amp;gt;&amp;lt;a href=&quot;#&quot; onclick=&quot;dp.sh.Toolbar.Command(\'ViewSource\',this);return false;&quot;&amp;gt;view plain&amp;lt;/a&amp;gt;&amp;lt;a href=&quot;#&quot; onclick=&quot;dp.sh.Toolbar.Command(\'CopyToClipboard\',this);return false;&quot;&amp;gt;copy to clipboard&amp;lt;/a&amp;gt;&amp;lt;a href=&quot;#&quot; onclick=&quot;dp.sh.Toolbar.Command(\'PrintSource\',this);return false;&quot;&amp;gt;print&amp;lt;/a&amp;gt;&amp;lt;a href=&quot;#&quot; onclick=&quot;dp.sh.Toolbar.Command(\'About\',this);return false;&quot;&amp;gt;?&amp;lt;/a&amp;gt;&amp;lt;/div&amp;gt;&amp;lt;/div&amp;gt;&amp;lt;ol class=&quot;dp-j&quot; start=&quot;1&quot;&amp;gt;&amp;lt;li class=&quot;alt&quot;&amp;gt;&amp;lt;span&amp;gt;&amp;lt;span class=&quot;keyword&quot;&amp;gt;import&amp;lt;/span&amp;gt;&amp;lt;span&amp;gt;&amp;amp;nbsp;java.util.*;&amp;amp;nbsp;&amp;amp;nbsp;&amp;lt;/span&amp;gt;&amp;lt;/span&amp;gt;&amp;lt;/li&amp;gt;&amp;lt;li class=&quot;&quot;&amp;gt;&amp;lt;span&amp;gt;&amp;amp;nbsp;&amp;amp;nbsp;&amp;lt;/span&amp;gt;&amp;lt;/li&amp;gt;&amp;lt;li class=&quot;alt&quot;&amp;gt;&amp;lt;span&amp;gt;&amp;lt;span class=&quot;keyword&quot;&amp;gt;public&amp;lt;/span&amp;gt;&amp;lt;span&amp;gt;&amp;amp;nbsp;&amp;lt;/span&amp;gt;&amp;lt;span class=&quot;keyword&quot;&amp;gt;class&amp;lt;/span&amp;gt;&amp;lt;span&amp;gt;&amp;amp;nbsp;myJava{&amp;amp;nbsp;&amp;amp;nbsp;&amp;lt;/span&amp;gt;&amp;lt;/span&amp;gt;&amp;lt;/li&amp;gt;&amp;lt;li class=&quot;&quot;&amp;gt;&amp;lt;span&amp;gt;&amp;amp;nbsp;&amp;amp;nbsp;&amp;lt;/span&amp;gt;&amp;lt;/li&amp;gt;&amp;lt;li class=&quot;alt&quot;&amp;gt;&amp;lt;span&amp;gt;System.out.println(&amp;lt;span class=&quot;string&quot;&amp;gt;&quot;this&amp;amp;nbsp;is&amp;amp;nbsp;test&amp;amp;nbsp;sysout&quot;&amp;lt;/span&amp;gt;&amp;lt;span&amp;gt;);&amp;amp;nbsp;&amp;amp;nbsp;&amp;lt;/span&amp;gt;&amp;lt;/span&amp;gt;&amp;lt;/li&amp;gt;&amp;lt;li class=&quot;&quot;&amp;gt;&amp;lt;span&amp;gt;&amp;amp;nbsp;&amp;amp;nbsp;&amp;lt;/span&amp;gt;&amp;lt;/li&amp;gt;&amp;lt;li class=&quot;alt&quot;&amp;gt;&amp;lt;span&amp;gt;}&amp;amp;nbsp;&amp;amp;nbsp;&amp;lt;/span&amp;gt;&amp;lt;/li&amp;gt;&amp;lt;/ol&amp;gt;&amp;lt;textarea style=&quot;display:none;&quot; class=&quot;originalCode&quot;&amp;gt;import java.util.*;&lt;br /&gt;&lt;br /&gt;public class myJava{&lt;br /&gt;&lt;br /&gt;System.out.println(&quot;this is test sysout&quot;);&lt;br /&gt;&lt;br /&gt;}&amp;lt;/textarea&amp;gt;&amp;lt;/div&amp;gt;&lt;/p&gt;','no','2013-09-05 13:45:45','2013-09-05 13:45:45',1,'no',NULL,'Pending','this is syntax highligher'),
 (20,'this is syntax highligher','&lt;p&gt;&amp;lt;pre&amp;gt;&lt;/p&gt;\r\n&lt;p&gt;&amp;lt;div class=&quot;dp-highlighter&quot;&amp;gt;&amp;lt;div class=&quot;bar&quot;&amp;gt;&amp;lt;div class=&quot;tools&quot;&amp;gt;&amp;lt;a href=&quot;#&quot; onclick=&quot;dp.sh.Toolbar.Command(\'ViewSource\',this);return false;&quot;&amp;gt;view plain&amp;lt;/a&amp;gt;&amp;lt;a href=&quot;#&quot; onclick=&quot;dp.sh.Toolbar.Command(\'CopyToClipboard\',this);return false;&quot;&amp;gt;copy to clipboard&amp;lt;/a&amp;gt;&amp;lt;a href=&quot;#&quot; onclick=&quot;dp.sh.Toolbar.Command(\'PrintSource\',this);return false;&quot;&amp;gt;print&amp;lt;/a&amp;gt;&amp;lt;a href=&quot;#&quot; onclick=&quot;dp.sh.Toolbar.Command(\'About\',this);return false;&quot;&amp;gt;?&amp;lt;/a&amp;gt;&amp;lt;/div&amp;gt;&amp;lt;/div&amp;gt;&amp;lt;ol class=&quot;dp-cpp&quot; start=&quot;1&quot;&amp;gt;&amp;lt;li class=&quot;alt&quot;&amp;gt;&amp;lt;span&amp;gt;&amp;lt;span&amp;gt;test&amp;amp;nbsp;&amp;amp;nbsp;&amp;lt;/span&amp;gt;&amp;lt;/span&amp;gt;&amp;lt;/li&amp;gt;&amp;lt;/ol&amp;gt;&amp;lt;textarea style=&quot;display:none;&quot; class=&quot;originalCode&quot;&amp;gt;test&amp;lt;/textarea&amp;gt;&amp;lt;/div&amp;gt;&lt;/p&gt;\r\n&lt;p&gt;&amp;lt;/pre&amp;gt;&lt;/p&gt;','no','2013-09-05 13:50:48','2013-09-05 13:50:48',1,'no',NULL,'Pending','this is syntax highligher');
/*!40000 ALTER TABLE `article_master` ENABLE KEYS */;


--
-- Definition of table `auth_master`
--

DROP TABLE IF EXISTS `auth_master`;
CREATE TABLE `auth_master` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `about_user` varchar(100) DEFAULT NULL,
  `isAdmin` varchar(3) NOT NULL,
  `isActive` varchar(3) NOT NULL,
  `modifiedDate` datetime NOT NULL,
  `createdDate` datetime NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `auth_master`
--

/*!40000 ALTER TABLE `auth_master` DISABLE KEYS */;
INSERT INTO `auth_master` (`userId`,`userName`,`password`,`firstName`,`lastName`,`email`,`mobile`,`address`,`gender`,`city`,`state`,`country`,`about_user`,`isAdmin`,`isActive`,`modifiedDate`,`createdDate`) VALUES
 (1,'afsar','bfbf8ca419c1c2b64f3c758759bd408b',NULL,NULL,'contactafsar@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'yes','yes','2013-09-03 20:44:56','2013-09-03 20:44:56');
/*!40000 ALTER TABLE `auth_master` ENABLE KEYS */;


--
-- Definition of table `category_master`
--

DROP TABLE IF EXISTS `category_master`;
CREATE TABLE `category_master` (
  `categoryId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(45) NOT NULL,
  `categoryDesc` varchar(250) NOT NULL,
  `isActive` varchar(3) NOT NULL,
  `modifiedDate` datetime NOT NULL,
  `createdDate` datetime NOT NULL,
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category_master`
--

/*!40000 ALTER TABLE `category_master` DISABLE KEYS */;
INSERT INTO `category_master` (`categoryId`,`categoryName`,`categoryDesc`,`isActive`,`modifiedDate`,`createdDate`) VALUES
 (1,'Java','This category deals with Core','yes','2013-09-04 19:14:26','2013-09-04 19:14:26'),
 (2,'JSP','This category deals with JSP article','yes','2013-09-04 19:14:55','2013-09-04 19:14:55'),
 (3,'Servlet','This category deals with Servlet article','yes','2013-09-04 19:15:08','2013-09-04 19:15:08'),
 (4,'Struts 1.x','This category deals with Struts 1.x article','yes','2013-09-04 19:15:25','2013-09-04 19:15:25'),
 (5,'Struts 2.x','This category will deals with Struts 2.x','yes','2013-09-05 19:19:48','2013-09-05 19:19:48');
/*!40000 ALTER TABLE `category_master` ENABLE KEYS */;


--
-- Definition of table `comments`
--

DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `url` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `body` text COLLATE utf8_unicode_ci NOT NULL,
  `articleId` int(10) unsigned NOT NULL,
  `userId` int(10) unsigned NOT NULL,
  `isActive` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifiedDate` datetime NOT NULL,
  `createdDate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `comments`
--

/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` (`id`,`name`,`url`,`email`,`body`,`articleId`,`userId`,`isActive`,`modifiedDate`,`createdDate`) VALUES
 (2,'Akash','','contactafsar@gmail.com','Akash\'s first comment',1,1,'yes','2013-09-04 17:25:59','2013-09-03 20:46:21'),
 (3,'afsar','','afsarkhan05@gmail.com','This is test commetn afsar',2,1,'yes','2013-09-07 18:42:35','2013-09-07 18:42:35');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;


--
-- Definition of table `image_master`
--

DROP TABLE IF EXISTS `image_master`;
CREATE TABLE `image_master` (
  `imageId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `localPath` varchar(300) DEFAULT NULL,
  `picasaPath` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`imageId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `image_master`
--

/*!40000 ALTER TABLE `image_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `image_master` ENABLE KEYS */;


--
-- Definition of table `recover_master`
--

DROP TABLE IF EXISTS `recover_master`;
CREATE TABLE `recover_master` (
  `recoverId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `securityQuestion` varchar(45) NOT NULL,
  `securityAnswer` varchar(45) NOT NULL,
  `modifiedDate` datetime NOT NULL,
  `createdDate` datetime NOT NULL,
  `userId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`recoverId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `recover_master`
--

/*!40000 ALTER TABLE `recover_master` DISABLE KEYS */;
INSERT INTO `recover_master` (`recoverId`,`securityQuestion`,`securityAnswer`,`modifiedDate`,`createdDate`,`userId`) VALUES
 (1,'my test?','success','2013-09-24 00:00:00','2013-09-24 00:00:00',1),
 (2,'my test?','success','2013-09-24 00:00:00','2013-09-24 00:00:00',2),
 (3,'my test?','success','2013-09-24 00:00:00','2013-09-24 00:00:00',3),
 (4,'my test?','success','2013-09-24 00:00:00','2013-09-24 00:00:00',4),
 (5,'fdjdkf','jfj','2013-09-24 00:00:00','2013-09-24 00:00:00',5),
 (6,'my name','afsar','2013-09-24 00:00:00','2013-09-24 00:00:00',6);
/*!40000 ALTER TABLE `recover_master` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
