INSERT INTO categories (id, created_at, name, description) VALUES (1, CURRENT_TIMESTAMP, '家电', '家用电器');
INSERT INTO categories (id, created_at, name, description) VALUES (2, CURRENT_TIMESTAMP, '美妆', '美妆个护');
INSERT INTO products (id, created_at, name, description, price, stock, category_id) VALUES
 (1, CURRENT_TIMESTAMP, '空气炸锅', '多功能空气炸锅', 399.00, 20, 1),
 (2, CURRENT_TIMESTAMP, '面膜', '补水面膜5片装', 59.00, 50, 2);
INSERT INTO users (id, created_at, username, password, role) VALUES (1, CURRENT_TIMESTAMP, 'admin', '$2a$10$TBR0oQra3hKlaRykGGBqM.EZo4PMBVXsfS5aXoaZy.gdkGFUTkGli', 'ADMIN');
