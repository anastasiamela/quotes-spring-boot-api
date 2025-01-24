CREATE TABLE IF NOT EXISTS quote (
    id INT AUTO_INCREMENT PRIMARY KEY,
    text TEXT NOT NULL,
    author VARCHAR(255)
);

INSERT INTO quote (author, text) VALUES ('Oscar Wilde', 'Be yourself; everyone else is already taken.');
INSERT INTO quote (author, text) VALUES ('Albert Einstein', 'Life is like riding a bicycle.');


