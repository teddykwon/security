create table Customer (

	id integer not null auto_increment,
	firstname varchar(30),
	lastname varchar(20),
	signupdate timestamp,
	primary key (id)
	
)


/* 회원 테이블 */
CREATE TABLE `users` (
	`seq` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '회원 고유번호',
	`parent_seq` int(11) NOT NULL DEFAULT '0' COMMENT '상위 회원 정보',
	`id` varchar(25) not null COMMENT '고유 id',
	`password` varchar(50) not null COMMENT '패스워드',
	`name` varchar(50) not null COMMENT '전체이름',
	`first_name` varchar(15) not null DEFAULT '' COMMENT '성',
	`last_name` varchar(35) not null DEFAULT '' COMMENT '이름',
	`zip1` varchar(3) NOT NULL COMMENT '우편 번호1',
	`zip2` varchar(3) NOT NULL COMMENT '우편 번호2',
	`addr1` varchar(3) NOT NULL COMMENT '주소1',
	`addr2` varchar(3) NOT NULL COMMENT '주소2',
	`addr_type` int(1) NOT NULL COMMENT '주소 타입',
	`status` int(1) NOT NULL DEFAULT '0' COMMENT '회원 상태 정보(0:대기, 1:승인, 2:탈퇴, 3:블락)',
	`type` int(1) NOT NULL DEFAULT '2' COMMENT '회원 타입(0:슈퍼관리자,1:관리자,2:일반,3:기업...)',
	`reg_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '등록시각',
	`update_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '업데이트 시작',
	PRIMARY KEY (`seq`),
	KEY `idx_parent_seq` (`parent_seq`),
	UNIQUE KEY `idx_id` (`id`),
	KEY `idx_name` (`name`),
	KEY `idx_first_name` (`first_name`),
	KEY `last_name` (`last_name`),
	KEY `idx_status` (`status`),
	KEY `idx_type` (`type`),
	KEY `idx_reg_time` (`reg_time`),
	KEY `idx_update_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='회원 정보';