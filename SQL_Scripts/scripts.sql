create schema 2024_batch; 
use 2024_batch;
create table product (ID int auto_increment,Name varchar(200),primary key (ID));
create table stock(id int auto_increment primary key, type varchar(200), qty  int);
create table mobile(product_brand varchar(50),product_price double,product_model varchar(50),arrived_date_time datetime);
create table charger(product_brand varchar(50),product_price double,product_model varchar(50),arrived_date_time datetime);
create table powerbank(product_brand varchar(50),product_price double,product_model varchar(50),arrived_date_time datetime);
create table backcover(product_brand varchar(50),product_price double,product_model varchar(50),arrived_date_time datetime);
create table headset(product_brand varchar(50),product_price double,product_model varchar(50),arrived_date_time datetime);
