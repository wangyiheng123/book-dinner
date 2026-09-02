create database dinnerBase;
use dinnerBase;
create table user(
   id int primary key auto_increment,
   username varchar(30),
   password varchar(30),
   name varchar(10)
);

use dinnerBase;
create table shipping(
    id int primary key auto_increment,
    userId INT(4),
    address varchar(30),
    sign varchar(4),
    name varchar(4),
    sex varchar(4),
    phone varchar(12),
    isDefault INT(4),
    status INT(4)
);

use dinnerBase;
create table town(
    id int primary key auto_increment,
    city varchar(13)
);

create table shop(
    id int primary key auto_increment,
    cityId INT,
    image varchar(30),
    name varchar(20),
    location varchar(50),
    sign varchar(50),
    score float(2,1),
    num INT,
    minute INT,
    distance float(2,1),
    created timestamp
);

use dinnerBase;
create table kind(
    id int primary key auto_increment,
    kindName varchar(6),
    num INT
);

create table weekData(
    id int primary key auto_increment,
    kindId INT,
    day varchar(4),
    num INT
);
insert into weekData values(null,1,'周一',90);
insert into weekData values(null,2,'周一',65);
insert into weekData values(null,3,'周一',102);
insert into weekData values(null,4,'周一',46);
insert into weekData values(null,5,'周一',74);
insert into weekData values(null,6,'周一',37);
insert into weekData values(null,7,'周一',51);
insert into weekData values(null,8,'周一',60);
insert into weekData values(null,1,'周二',67);
insert into weekData values(null,2,'周二',74);
insert into weekData values(null,3,'周二',67);
insert into weekData values(null,4,'周二',62);
insert into weekData values(null,5,'周二',62);
insert into weekData values(null,6,'周二',68);
insert into weekData values(null,7,'周二',43);
insert into weekData values(null,8,'周二',84);
insert into weekData values(null,1,'周三',93);
insert into weekData values(null,2,'周三',81);
insert into weekData values(null,3,'周三',71);
insert into weekData values(null,4,'周三',76);
insert into weekData values(null,5,'周三',68);
insert into weekData values(null,6,'周三',52);
insert into weekData values(null,7,'周三',62);
insert into weekData values(null,8,'周三',76);
insert into weekData values(null,1,'周四',47);
insert into weekData values(null,2,'周四',93);
insert into weekData values(null,3,'周四',59);
insert into weekData values(null,4,'周四',84);
insert into weekData values(null,5,'周四',86);
insert into weekData values(null,6,'周四',84);
insert into weekData values(null,7,'周四',71);
insert into weekData values(null,8,'周四',65);
insert into weekData values(null,1,'周五',61);
insert into weekData values(null,2,'周五',112);
insert into weekData values(null,3,'周五',88);
insert into weekData values(null,4,'周五',89);
insert into weekData values(null,5,'周五',102);
insert into weekData values(null,6,'周五',91);
insert into weekData values(null,7,'周五',92);
insert into weekData values(null,8,'周五',81);
insert into weekData values(null,1,'周六',76);
insert into weekData values(null,2,'周六',84);
insert into weekData values(null,3,'周六',102);
insert into weekData values(null,4,'周六',93);
insert into weekData values(null,5,'周六',71);
insert into weekData values(null,6,'周六',99);
insert into weekData values(null,7,'周六',97);
insert into weekData values(null,8,'周六',57);
insert into weekData values(null,1,'周日',80);
insert into weekData values(null,2,'周日',93);
insert into weekData values(null,3,'周日',66);
insert into weekData values(null,4,'周日',103);
insert into weekData values(null,5,'周日',94);
insert into weekData values(null,6,'周日',83);
insert into weekData values(null,7,'周日',81);
insert into weekData values(null,8,'周日',71);

use dinnerBase;
create table food(
   id int primary key auto_increment,
   shopId INT,   店铺ID
   name varchar(50),  商品名称
   image varchar(20),  图片地址
   price Float(4,2),  价格
   kindId INT,  种类ID
   num INT,  月售量
   chargeMixture varchar(50), 原料
   mouthFeel varchar(10),  口味
   temperature varchar(10), 温度
   meatAndVegetables varchar(10), 荤素
   method varchar(10), 制作方法
   minute varchar(10),  制作时间
   weight varchar(10)  分量
);

insert into food values(null,1,'卤味大全','/img/shop1/shop1-2.jpg',18.88,1,115,'鸭翅，鸭腿，鸭脖','麻辣','温热','荤','熏蒸','提前制作好','150g');
insert into food values(null,1,'麻辣鸭翅','/img/shop1/shop1-4.jpg',16.21,1,130,'鸭翅','麻辣','温热','荤','熏蒸','提前制作好','150g');
insert into food values(null,1,'原味鸭肝','/img/shop1/shop1-5.jpg',22.12,1,61,'鸭肝','麻辣','温热','荤','熏蒸','提前制作好','150g');
insert into food values(null,1,'原味鸭腿','/img/shop1/shop1-6.jpg',13.45,1,84,'鸭腿','麻辣','温热','荤','熏蒸','提前制作好','150g');
insert into food values(null,1,'卤味鸭腿','/img/shop1/shop1-7.jpg',23.14,1,97,'鸭腿','麻辣','温热','荤','熏蒸','提前制作好','150g');
insert into food values(null,1,'麻辣鸭脖','/img/shop1/shop1-8.jpg',22.65,1,114,'鸭脖','麻辣','温热','荤','熏蒸','提前制作好','150g');
insert into food values(null,1,'卤味鸡爪','/img/shop1/shop1-9.jpg',15.23,1,39,'鸡爪','麻辣','温热','荤','熏蒸','提前制作好','150g');

insert into food values(null,2,'猪肉串','/img/shop2/shop2-2.jpg',2.00,2,160,'猪肉','微辣','热','荤','烧烤','1~10分钟','1串');
insert into food values(null,2,'烤香菇','/img/shop2/shop2-3.jpg',1.00,2,120,'香菇','微辣','热','素','烧烤','1~10分钟','1串');
insert into food values(null,2,'烤牛蛙','/img/shop2/shop2-4.jpg',3.00,2,86,'牛蛙','微辣','热','荤','烧烤','1~10分钟','1串');
insert into food values(null,2,'烤香肠','/img/shop2/shop2-5.jpg',2.00,2,43,'猪肉','微辣','热','荤','烧烤','1~10分钟','1串');
insert into food values(null,2,'干豆腐串','/img/shop2/shop2-6.jpg',2.00,2,77,'干豆腐','微辣','热','素','烧烤','1~10分钟','10串');
insert into food values(null,2,'烤辣椒','/img/shop2/shop2-7.jpg',1.00,2,63,'辣椒','微辣','热','素','烧烤','1~10分钟','1串');
insert into food values(null,2,'金针菇','/img/shop2/shop2-8.jpg',3.00,2,101,'金针菇','微辣','热','素','烧烤','1~10分钟','1盘');
insert into food values(null,2,'烤土豆片','/img/shop2/shop2-9.jpg',1.00,2,151,'土豆','微辣','热','素','烧烤','1~10分钟','1串');
insert into food values(null,2,'烤鸡肝','/img/shop2/shop2-10.jpg',2.00,2,46,'鸡肝','微辣','热','荤','烧烤','1~10分钟','1串');

insert into food values(null,3,'葱爆小龙虾','/img/shop3/shop3-1.jpg',20.66,3,61,'小龙虾','葱香','热','荤','炒制','1~10分钟','一盘');
insert into food values(null,3,'清蒸小龙虾','/img/shop3/shop3-2.jpg',23.98,3,95,'小龙虾','鲜甜','热','荤','清蒸','1~10分钟','一盘');
insert into food values(null,3,'炒小龙虾','/img/shop3/shop3-3.jpg',22.14,3,64,'小龙虾','咸香','热','荤','炒制','1~10分钟','一盘');
insert into food values(null,3,'酱香小龙虾','/img/shop3/shop3-4.jpg',27.63,3,47,'小龙虾','香辣','热','荤','炒制','1~10分钟','一盘');
insert into food values(null,3,'炖小龙虾','/img/shop3/shop3-5.jpg',21.34,3,76,'小龙虾','咸香','热','荤','炖','1~10分钟','一盘');
insert into food values(null,3,'葱炖小龙虾','/img/shop3/shop3-6.jpg',23.23,3,59,'小龙虾','葱香','热','荤','炒制','1~10分钟','一盘');
insert into food values(null,3,'麻辣小龙虾','/img/shop3/shop3-7.jpg',25.88,3,182,'小龙虾','麻辣','热','荤','炒制','1~10分钟','一盘');
insert into food values(null,3,'麻辣小龙虾调味料','/img/shop3/shop3-8.jpg',10.00,3,39,'食用盐，水','麻辣','凉','素','炒制','提前制作好','一袋');

insert into food values(null,4,'豆角炒肉','/img/shop4/shop4-1.jpg',15.88,4,55,'豆角，肉','咸香','热','荤','炒制','10~15分钟','1盘');
insert into food values(null,4,'锅包肉','/img/shop4/shop4-2.jpg',26.88,4,130,'猪肉','酸甜','热','荤','炒制','10~15分钟','1盘');
insert into food values(null,4,'鱼香肉丝','/img/shop4/shop4-3.jpg',18.88,4,78,'猪肉','微辣，酸甜','热','荤','炒制','10~15分钟','1盘');
insert into food values(null,4,'麻辣鳕鱼','/img/shop4/shop4-4.jpg',25.88,4,120,'鳕鱼','麻辣','热','荤','炒制','10~15分钟','1盘');
insert into food values(null,4,'炒木耳','/img/shop4/shop4-5.jpg',13.66,4,99,'木耳，辣椒','咸香','热','素','炒制','10~15分钟','1盘');
insert into food values(null,4,'糖醋鲫鱼','/img/shop4/shop4-6.jpg',32.66,4,75,'鲫鱼','酸甜','热','荤','炒制','10~15分钟','1盘');
insert into food values(null,4,'小葱拌豆腐','/img/shop4/shop4-7.jpg',10.66,4,102,'葱，豆腐','咸香','凉','素','凉拌','10~15分钟','1盘');

insert into food values(null,5,'华夫饼','/img/shop5/shop5-1.jpg',15.88,5,88,'小麦粉','甜味','凉','面制品','烘烤','10~15分钟','50g');
insert into food values(null,5,'巧克力奶油蛋糕','/img/shop5/shop5-2.jpg',16.66,5,65,'小麦粉','甜味','凉','面制品','烘烤','10~15分钟','50g');
insert into food values(null,5,'奶油蛋糕','/img/shop5/shop5-3.jpg',10.66,5,77,'小麦粉','甜味','凉','面制品','烘烤','10~15分钟','50g');
insert into food values(null,5,'草莓派','/img/shop5/shop5-4.jpg',21.88,5,91,'小麦粉','甜味','凉','面制品','烘烤','10~15分钟','50g');
insert into food values(null,5,'餐前小蛋糕','/img/shop5/shop5-5.jpg',15.88,5,106,'小麦粉','甜味','凉','面制品','烘烤','10~15分钟','50g');
insert into food values(null,5,'精品小蛋糕','/img/shop5/shop5-6.jpg',14.88,5,113,'小麦粉','甜味','凉','面制品','烘烤','10~15分钟','50g');
insert into food values(null,5,'提拉米苏','/img/shop5/shop5-7.jpg',18.88,5,130,'小麦粉','甜味','凉','面制品','烘烤','10~15分钟','50g');
insert into food values(null,5,'餐后小蛋糕','/img/shop5/shop5-8.jpg',16.88,5,86,'小麦粉','甜味','凉','面制品','烘烤','10~15分钟','50g');


insert into food values(null,6,'玉米培根披萨','/img/shop6/shop6-1.jpg',18.88,6,79,'小麦粉','咸香','热','面制品','烘烤','20~25分钟','6寸');
insert into food values(null,6,'芝士火腿披萨','/img/shop6/shop6-2.jpg',22.88,6,106,'小麦粉','咸香','热','面制品','烘烤','20~25分钟','6寸');
insert into food values(null,6,'香菇披萨','/img/shop6/shop6-3.jpg',19.88,6,132,'小麦粉','咸香','热','面制品','烘烤','20~25分钟','6寸');
insert into food values(null,6,'火腿披萨','/img/shop6/shop6-4.jpg',14.88,6,147,'小麦粉','咸香','热','面制品','烘烤','20~25分钟','8寸');
insert into food values(null,6,'虾仁披萨','/img/shop6/shop6-5.jpg',25.88,6,159,'小麦粉','咸香','热','面制品','烘烤','20~25分钟','6寸');
insert into food values(null,6,'草莓披萨','/img/shop6/shop6-6.jpg',21.88,6,162,'小麦粉','咸香','热','面制品','烘烤','20~25分钟','6寸');
insert into food values(null,6,'榴莲披萨','/img/shop6/shop6-7.jpg',16.88,6,186,'小麦粉','咸香','热','面制品','烘烤','20~25分钟','6寸');
insert into food values(null,6,'菠萝披萨','/img/shop6/shop6-8.jpg',22.88,6,91,'小麦粉','咸香','热','面制品','烘烤','20~25分钟','6寸');

insert into food values(null,7,'毛肚','/img/shop7/shop7-1.jpg',5.00,7,120,'毛肚','麻辣','凉','荤','生鲜','无需准备','10串');
insert into food values(null,7,'玉米','/img/shop7/shop7-2.jpg',2.00,7,131,'玉米粒','鲜甜','凉','素','生鲜','无需准备','10串');
insert into food values(null,7,'鸭肠','/img/shop7/shop7-3.jpg',5.00,7,141,'鸭肠','卤味','凉','荤','生鲜','无需准备','10串');
insert into food values(null,7,'麻辣牛肉','/img/shop7/shop7-4.jpg',5.00,7,199,'牛肉','麻辣','凉','荤','生鲜','无需准备','10串');
insert into food values(null,7,'五香牛肉','/img/shop7/shop7-5.jpg',5.00,7,167,'牛肉','五香','凉','荤','生鲜','无需准备','10串');
insert into food values(null,7,'干豆腐','/img/shop7/shop7-6.jpg',2.00,7,163,'干豆腐','咸香','凉','素','生鲜','无需准备','10串');


insert into food values(null,8,'玉米培根披萨','/img/shop8/shop8-1.jpg',18.88,8,79,'小麦粉','咸香','热','面制品','烘烤','20~25分钟','6寸');
insert into food values(null,8,'芝士火腿披萨','/img/shop8/shop8-2.jpg',22.88,8,106,'小麦粉','咸香','热','面制品','烘烤','20~25分钟','6寸');
insert into food values(null,8,'香菇披萨','/img/shop8/shop8-3.jpg',19.88,8,132,'小麦粉','咸香','热','面制品','烘烤','20~25分钟','6寸');
insert into food values(null,8,'火腿披萨','/img/shop8/shop8-4.jpg',14.88,8,147,'小麦粉','咸香','热','面制品','烘烤','20~25分钟','8寸');
insert into food values(null,8,'虾仁披萨','/img/shop8/shop8-5.jpg',25.88,8,159,'小麦粉','咸香','热','面制品','烘烤','20~25分钟','6寸');
insert into food values(null,8,'草莓披萨','/img/shop8/shop8-6.jpg',21.88,8,162,'小麦粉','咸香','热','面制品','烘烤','20~25分钟','6寸');
insert into food values(null,8,'榴莲披萨','/img/shop8/shop8-7.jpg',16.88,8,186,'小麦粉','咸香','热','面制品','烘烤','20~25分钟','6寸');
insert into food values(null,8,'菠萝披萨','/img/shop8/shop8-8.jpg',22.88,8,91,'小麦粉','咸香','热','面制品','烘烤','20~25分钟','6寸');

use dinnerBase;
create table cart(
   id int primary key auto_increment,
   userId INT,
   shopId INT,
   foodId INT,
   num INT
);

use dinnerBase;
create table cartBack(
   id int primary key auto_increment,
   userId INT,
   shopId INT,
   foodId INT,
   num INT
);

use dinnerBase;
create table rider(
   id int primary key auto_increment,
   name varchar(10),
   age varchar(3),
   phone varchar(13),
   status INT(4)
);

insert into rider values(null,'张吉威','25','16254789854',0);
insert into rider values(null,'王俊民','31','18546524561',0);
insert into rider values(null,'黄浩','29','13545986524',0);
insert into rider values(null,'柯桥喜','30','17895645213',0);
insert into rider values(null,'黄文旺','25','14698526331',0);
insert into rider values(null,'方兆宇','32','19745688524',0);
insert into rider values(null,'徐越平','31','14623541587',0);
insert into rider values(null,'李元凯','27','16125789456',0);
insert into rider values(null,'赵括','29','18745696542',0);
insert into rider values(null,'李辉','27','16125478982',0);

create table item(
   id int primary key auto_increment,
   userId INT,
   addressId INT,
   riderId INT,
   shopId INT,
   sendPrice INT,
   totalPrice varchar(12),
   mark varchar(30),
   tableNum varchar(15),
   status INT
);

use dinnerBase;
create table advice(
   id int primary key auto_increment,
   shopId INT,
   riderId INT,
   shopStar INT,
   riderStar INT,
   comment varchar(33)
);

create table file(
   id int primary key auto_increment,
   adviceId INT,
   image varchar(60)
);

create table advicestatus(
   id int primary key auto_increment,
   itemId INT,
   status INT
);

use dinnerBase;
create table userstatus(
   id int primary key auto_increment,
   userId INT,
   status INT
);

create table shopimage(
   id int primary key auto_increment,
   shopId INT,
   image varchar(60)
);

