create database word_game_db character set utf8;

use word_game_db;
create table words
(
    word_id int          not null AUTO_INCREMENT,
    word_en VARCHAR(255) not null,
    word_az varchar(255) not null,
    primary key (word_id)
);

create table competitors_info
(
    com_id       int         not null AUTO_INCREMENT,
    com_name     varchar(30) not null,
    com_surname  varchar(30) not null,
    com_point    int         not null,
    com_position boolean     not null,
    primary key (com_id)
);
create table competitors_base
(
    com_base_id       int         not null auto_increment,
    com_base_username varchar(50) not null,
    com_base_password varchar(50) not null,
    com_base_com_info int,
    primary key (com_base_id),
    foreign key (com_base_com_info) references competitors_info (com_id)
);


delimiter $$
create procedure insertCompetitor(in param1 varchar(50), param2 VARCHAR(50), param3 varchar(30),
                                  param4 varchar(30), param5 int, param6 int)
begin
    insert into competitors_base(com_base_username, com_base_password)
    values (param1, param2);

    insert into competitors_info(com_name, com_surname, com_point, com_position)
    values (param3, param4, param5, param6);

    update competitors_base
    set com_base_com_info=(select max(com_id) from competitors_info)
    where com_base_id = (select max(com_id) from competitors_info);

end $$
delimiter ;

delimiter $$
create procedure deleteCompetitor(in param1 INT)
begin
    delete
    from competitors_base
    where com_base_id = param1;
    delete
    from competitors_info
    where com_id = param1;
end $$
delimiter ;


delimiter $$
create procedure updateCompetitor(in param1 int, param2 VARCHAR(50), param3 VARCHAR(50),
                                  param4 VARCHAR(30), param5 VARCHAR(30), param6 int, param7 boolean)
begin
    update competitors_base
    set com_base_username=param2,
        com_base_password=param3
    where com_base_id = param1;
    update competitors_info
    set com_name=param4,
        com_surname=param5,
        com_point=param6,
        com_position=param7
    where com_id=param1;
    end $$

delimiter ;

call insertCompetitor('admin','92668751','Admin','Admin',0,1);
insert into words(word_en, word_az) values('Word','Söz');
insert into words(word_en, word_az) values('Apple','Alma');
insert into words(word_en, word_az) values('Pen','Qələm');
insert into words(word_en, word_az) values('Pencil','Karandaş');
insert into words(word_en, word_az) values('Pineapple','Ananas');
insert into words(word_en, word_az) values('World','Dünya');
insert into words(word_en, word_az) values('Paper','Kağız');
insert into words(word_en, word_az) values('Hello','Salam');
insert into words(word_en, word_az) values('New','Yeni');
insert into words(word_en, word_az) values('Value','Dəyər');
insert into words(word_en, word_az) values('Select','Seçmək');
insert into words(word_en, word_az) values('Delete','Silmək');
insert into words(word_en, word_az) values('Connect','Qoşulmaq');
insert into words(word_en, word_az) values('You','Sən, Siz');
insert into words(word_en, word_az) values('Check','Yoxlamaq');
insert into words(word_en, word_az) values('Insert','Daxil etmək');
insert into words(word_en, word_az) values('Place','Yer');
insert into words(word_en, word_az) values('Particular','Xüsusi');
insert into words(word_en, word_az) values('Member','Üzv');
insert into words(word_en, word_az) values('Belong','Aid olmaq');