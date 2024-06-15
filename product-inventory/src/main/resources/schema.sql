-- Create product table
create table if not exists product
(
    id          INT AUTO_INCREMENT primary key,
    name        varchar(255) not null,
    description text,
    price       decimal(12,2),
    in_stock    int
);

-- Create orders table
create table if not exists "orders"
(
    id          INT AUTO_INCREMENT primary key,
    user_name   varchar(255) not null
);

-- Create order_item table
create table if not exists order_item
(
    id          INT AUTO_INCREMENT primary key,
    order_id    INT not null,
    product_id  INT not null,
    quantity    int,
    constraint fk_order foreign key (order_id) references "orders" (id),
    constraint fk_product foreign key (product_id) references product (id)
);
