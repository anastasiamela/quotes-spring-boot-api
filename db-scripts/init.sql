CREATE TABLE IF NOT EXISTS quotes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    text TEXT NOT NULL,
    author VARCHAR(255)
);

INSERT INTO quotes (author, text) VALUES ('Oscar Wilde', 'Be yourself; everyone else is already taken.');
INSERT INTO quotes (author, text) VALUES ('Albert Einstein', 'Life is like riding a bicycle.');


