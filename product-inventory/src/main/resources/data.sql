insert into product (name, description, price, in_stock)
values ('MacBook Pro 14', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum volutpat ac nibh nec consequat. Nam eget varius nibh. Nulla ligula justo, convallis in vulputate a, convallis vitae velit. Aliquam erat volutpat. Mauris vel convallis metus, at ultricies magna. Donec vitae porttitor libero, at euismod odio. Nullam pharetra, turpis vel pharetra egestas, arcu orci placerat metus, a interdum odio ex ac leo.

', 1999.00, 2),
       ('Samsung S22+', 'previous year model' , 780.00, 0),
       ('iPhone 14 Pro', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum volutpat ac nibh nec consequat. Nam eget varius nibh. Nulla ligula justo, convallis in vulputate a, convallis vitae velit. Aliquam erat volutpat. Mauris vel convallis metus, at ultricies magna. Donec vitae porttitor libero, at euismod odio. Nullam pharetra, turpis vel pharetra egestas, arcu orci placerat metus, a interdum odio ex ac leo.

', 999.00, 10),
       ('iPhone 15 Pro Max', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum volutpat ac nibh nec consequat. Nam eget varius nibh. Nulla ligula justo, convallis in vulputate a, convallis vitae velit. Aliquam erat volutpat. Mauris vel convallis metus, at ultricies magna. Donec vitae porttitor libero, at euismod odio. Nullam pharetra, turpis vel pharetra egestas, arcu orci placerat metus, a interdum odio ex ac leo.

', 1199.00, 10),
       ('iPhone 12', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum volutpat ac nibh nec consequat. Nam eget varius nibh. Nulla ligula justo, convallis in vulputate a, convallis vitae velit. Aliquam erat volutpat. Mauris vel convallis metus, at ultricies magna. Donec vitae porttitor libero, at euismod odio. Nullam pharetra, turpis vel pharetra egestas, arcu orci placerat metus, a interdum odio ex ac leo.

', 890.00, 6),
       ('AirPods Pro', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum volutpat ac nibh nec consequat. Nam eget varius nibh. Nulla ligula justo, convallis in vulputate a, convallis vitae velit. Aliquam erat volutpat. Mauris vel convallis metus, at ultricies magna. Donec vitae porttitor libero, at euismod odio. Nullam pharetra, turpis vel pharetra egestas, arcu orci placerat metus, a interdum odio ex ac leo.

', 399.00, 18),
       ('Sony WH1000XM', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum volutpat ac nibh nec consequat. Nam eget varius nibh. Nulla ligula justo, convallis in vulputate a, convallis vitae velit. Aliquam erat volutpat. Mauris vel convallis metus, at ultricies magna. Donec vitae porttitor libero, at euismod odio. Nullam pharetra, turpis vel pharetra egestas, arcu orci placerat metus, a interdum odio ex ac leo.

', 499.00, 5),
       ('MacBook Pro 16', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum volutpat ac nibh nec consequat. Nam eget varius nibh. Nulla ligula justo, convallis in vulputate a, convallis vitae velit. Aliquam erat volutpat. Mauris vel convallis metus, at ultricies magna. Donec vitae porttitor libero, at euismod odio. Nullam pharetra, turpis vel pharetra egestas, arcu orci placerat metus, a interdum odio ex ac leo.

', 2499.00, 3)
;

-- Inserting orders
insert into "orders" (user_name) values ('ramil');
insert into "orders" (user_name) values ('tomcruise62');
insert into "orders" (user_name) values ('messi1987');

-- Inserting order items
-- Replace the product IDs and quantities with actual existing products and desired quantities
insert into order_item (order_id, product_id, quantity)
values
    (1, 1, 1), -- Order for john_doe: MacBook Pro 14, quantity 1
    (1, 3, 2), -- Order for john_doe: iPhone 14 Pro, quantity 2
    (2, 2, 1), -- Order for jane_smith: Samsung S22+, quantity 1
    (3, 4, 1); -- Order for alice_wonderland: iPhone 15 Pro Max, quantity 1
