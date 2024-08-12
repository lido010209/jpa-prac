INSERT INTO article (title, content, writer)
VALUES
('My universe', 'My name is Luna', 'Luna Do'),
('My world', 'My world is everything I can touch', 'Luna Do'),
('AI', 'AI changes our world. The appearance of AI is notable in this era, which is the fifth industrial revolution', 'None'),
('Java', 'Java is programming language. But that is the language of computer as well', 'Luna Do');

INSERT INTO comment (content, writer, article_id)
VALUES
('Beautiful!!!', 'Alias', 1),
('So weird', 'Bae', 1),
('Wow^-^','Nice', 2),
('That''s right^-^','Cian', 3);

INSERT INTO author(name, debut_year)
VALUES
('Luna Do', 2001),
('Anna', 2015),
('Ali', 2022),
('Lido', 2020);

INSERT INTO book(title, summary, rating, author_id)
VALUES
('My universe', 'The life is reflected by author''view', 3.7, 1),
('My world', 'War and peace is necessary', 2.5, 3),
('Java', 'The knowledge of Java', 4.1, 2),
('Python', 'The difference and effortlessness of Python', 3.0, 4);

INSERT INTO shop(name, home_page)
VALUES
('Coupang','https://m.coupang.com/'),
('Lotte','https://lotte.com/'),
('ABC mart','https://abcmart.a-rt.com/'),
('Starbucks','https://www.starbucks.com/'),
('Olive Young','https://oliveyoung.com/'),
('CJ','https://cj.com/'),
('Emart','https://emart.com/'),
('KT','https://kt.com/'),
('Louis Vuitton','https://louisvuitton.com/');
