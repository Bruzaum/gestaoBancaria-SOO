CREATE TABLE `clients` (
  `id` integer not null PRIMARY KEY,
  `name` varchar(255)  not null,
  `cpf` varchar(255)  not null,
  `account_value` number default 0.0 not null,

  CONSTRAINT client_unique_cpf UNIQUE (cpf)
);

CREATE TABLE `transactions` (
  `id` integer not null PRIMARY KEY,
  `user_id` INTEGER  not null,
  `type` varchar(255)  not null,
  `value` number not null,
);
