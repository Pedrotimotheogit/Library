
INSERT INTO authors (author_name)
VALUES
    ('J. R. R. Tolkien'),
    ('George Orwell'),
    ('Machado de Assis'),
    ('Clarice Lispector'),
    ('Agatha Christie'),
    ('Stephen King'),
    ('Júlio Verne'),
    ('Mary Shelley'),
    ('F. Scott Fitzgerald'),
    ('José Saramago');



INSERT INTO books (title, quantity, release_date)
VALUES
    ('O Senhor dos Anéis', 5, '1954-07-29'),
    ('1984', 3, '1949-06-08'),
    ('Dom Casmurro', 4, '1899-01-01'),
    ('A Hora da Estrela', 2, '1977-10-01'),
    ('Assassinato no Expresso do Oriente', 3, '1934-01-01'),
    ('It: A Coisa', 4, '1986-09-15'),
    ('Viagem ao Centro da Terra', 2, '1864-11-25'),
    ('Frankenstein', 3, '1818-01-01'),
    ('O Grande Gatsby', 2, '1925-04-10'),
    ('Ensaio sobre a Cegueira', 3, '1995-01-01');



INSERT INTO book_authors (book_id, author_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10);


INSERT INTO readers (reader_name)
VALUES
    ('João Silva'),
    ('Maria Souza'),
    ('Pedro Santos'),
    ('Ana Oliveira'),
    ('Lucas Pereira'),
    ('Juliana Costa'),
    ('Gabriel Almeida'),
    ('Beatriz Rodrigues'),
    ('Rafael Martins'),
    ('Camila Ferreira');



INSERT INTO borrowed_books (
    book_id,
    reader_id,
    borrow_date,
    return_date
)
VALUES
    (1, 1, '2026-08-01', '2026-08-10'),
    (2, 2, '2026-08-03', '2026-08-12'),
    (3, 3, '2026-08-05', NULL),
    (4, 4, '2026-08-06', '2026-08-15'),
    (5, 5, '2026-08-08', NULL),
    (6, 6, '2026-08-10', '2026-08-18'),
    (7, 7, '2026-08-12', NULL),
    (8, 8, '2026-08-14', '2026-08-20'),
    (9, 9, '2026-08-16', NULL),
    (10, 10, '2026-08-18', '2026-08-23');