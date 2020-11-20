/*
Drop user and database in case they already exist
*/
drop database thecushiondb;
drop user thecushion;

/*
Create database user and database with template0
Then connecting to database
*/
create user thecushion with password 'password';
create database thecushiondb with template=template0 owner=thecushion;
\connect thecushiondb;

/*
Grant privileges on tables and sequences to database user
*/
alter default privileges grant all on tables to thecushion;
alter default privileges grant all on sequences to thecushion;

/*
Creating cushion_user table
One to many relationship with cart items
One to many relationship with wishlist items
One to many relationship with purchase
*/
create table cushion_user(
    user_id integer primary key not null,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    email varchar(40) not null,
    password text not null
);

/*
Creating item table
One to one relationship with wishlist item
One to one relationship with cart item
*/
create table item(
    item_id integer primary key not null,
    quantity integer not null,
    image_uri text,
    price numeric(10,2) not null,
    name varchar(50) not null,
    description text,
    category varchar(20)
);

/*
Creating purchase table
One to one relationship with user
One to one relationship with item
*/
create table purchase(
    purchase_id integer primary key not null,
    user_id integer not null,
    item_id integer not null
);
/*
Adding user ID as foreign key constraint
Adding item ID as foreign key constraint
*/
alter table purchase add constraint purchase_user_fk
foreign key (user_id) references cushion_user(user_id);

/*
Creating wishlist item table
Many to one relationship with user
One to one relationship with item
*/
create table wishlist_item(
    wishlist_item_id integer primary key not null,
    user_id integer not null,
    item_id integer not null
);
/*
Adding user ID as foreign key constraint
Adding item ID as a foreign key constraint
*/
alter table wishlist_item add constraint wishlist_item_user_fk
foreign key (user_id) references cushion_user(user_id);
alter table wishlist_item add constraint wishlist_item_item_fk
foreign key (item_id) references item(item_id);

/*
Creating wishlist item table
Many to one relationship with user
One to one relationship with item
*/
create table cart_item(
    cart_item_id integer primary key not null,
    user_id integer not null,
    item_id integer not null,
    quantity integer not null
);
/*
Adding user ID as foreign key constraint
Adding item ID as a foreign key constraint
*/
alter table cart_item add constraint cart_item_user_fk
foreign key (user_id) references cushion_user(user_id);
alter table cart_item add constraint cart_item_item_fk
foreign key (item_id) references item(item_id);
/*
Crating ID sequences for all tables
*/
create sequence cushion_user_seq increment 1 start 1;
create sequence item_seq increment 1 start 1;
create sequence purchase_seq increment 1 start 1;
create sequence wishlist_item_seq increment 1 start 1;
create sequence cart_item_seq increment 1 start 1;