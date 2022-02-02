use bank;

create table Customer(
	CustomerID int not null,
    CustomerName varchar(35),
    CustomerPassword varchar(35),
    CustomerAge int
);

alter table customer
add constraint pk1 primary key(CustomerID);

create table Account(
	CustomerID int not null,
    AccountType varchar(35),
    BalanceAmmount numeric
);

alter table Account
add constraint fk1 foreign key(CustomerID) references customer(customerid);

insert into customer values(1, 'Deepak', 'HELLO', 22);

insert into account values(1, 'Savings', 50000);

UPDATE account
SET AccountType = 'Saving'
where CustomerID = 1;
