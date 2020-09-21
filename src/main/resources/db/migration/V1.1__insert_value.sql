

insert into school(name,city,state,address) values('UMD','College Park','MD','8204 baltimovre ave');
insert into school(name,city,state,address) values('GWU','DC','DC','123123 street');
insert into school(name,city,state,address) values('Skidmore College','Saratogo Spring','NY','815 N Broadway');

insert into account (id, school_id, first_name, last_name,address,account_name,password, balance) values
(1, 1,'Minfeng', 'Wu', '123123  street', 'MW','111', 9999 );
insert into account (id, school_id, first_name, last_name,address,account_name,password, balance) values
(2, 1,'Shihan', 'Lin', '123123  street','Lin','000' , 9999 );

insert into item(name,avaliability, brand, category, price) values('mac pro 2020 13 inch', true, 'Apple','Computer', 200)
insert into item(name,avaliability, brand, category, price) values('mac pro 2019 13 inch', true, 'Apple','Computer', 150)

insert into order_history(account_id,item_id,start_day,end_day) values(1,1,'2020-5-20','2020-12-20');
insert into order_history(account_id,item_id,start_day,end_day) values(1,2,'2020-7-30','2020-10-30');
insert into order_history(account_id,item_id,start_day,end_day) values(2,1,'2020-8-30','2020-9-30');
