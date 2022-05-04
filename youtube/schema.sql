create table if not exists sentiment (
  url_id integer primary key autoincrement,
  url_addr1 string not null,
  title string not null,
  sent string not null,
  percentage float
);