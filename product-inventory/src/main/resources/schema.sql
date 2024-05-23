create table if not exists product
(
    id          INT AUTO_INCREMENT primary key,
    name     varchar(255) not null,
    description     text,
    price   decimal(12,2),
    in_stock    int
);