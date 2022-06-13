CREATE TABLE employee
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(40)  NOT NULL,
    `password` varchar(100) NOT NULL
);

CREATE TABLE movie
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    title        varchar(256) NOT NULL,
    director     varchar(256),
    description  varchar(1024),
    duration_min int
);

CREATE TABLE room
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    `name`   varchar(32) NOT NULL,
    seats_no int
);

CREATE TABLE screening
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    movie_id        int NOT NULL,
    room_id         int NOT NULL,
    screening_start timestamp,
    FOREIGN KEY (movie_id) REFERENCES movie (id),
    FOREIGN KEY (room_id) REFERENCES room (id)
);

CREATE TABLE seat
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    `row`   char NOT NULL,
    number  int  NOT NULL,
    room_id int  NOT NULL,
    FOREIGN KEY (room_id) REFERENCES room (id)
);

CREATE TABLE reservation
(
    id                   int AUTO_INCREMENT PRIMARY KEY,
    screening_id         int NOT NULL,
    employee_reserved_id int,
    employee_paid_id     int,
    reservation_contact  varchar(1024),
    paid                 bool,
    active               bool,
    FOREIGN KEY (screening_id) REFERENCES screening (id),
    FOREIGN KEY (employee_reserved_id) REFERENCES employee (id),
    FOREIGN KEY (employee_paid_id) REFERENCES employee (id)
);

CREATE TABLE seat_reserved
(
    id             int AUTO_INCREMENT PRIMARY KEY,
    seat_id        int NOT NULL,
    reservation_id int NOT NULL,
    screening_id   int NOT NULL,
    FOREIGN KEY (seat_id) REFERENCES seat (id),
    FOREIGN KEY (reservation_id) REFERENCES reservation (id),
    FOREIGN KEY (screening_id) REFERENCES screening (id)
);