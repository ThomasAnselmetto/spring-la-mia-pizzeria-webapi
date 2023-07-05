INSERT INTO `pizzas`(`id`, `prezzo`, `descrizione`,`ingredienti`, `nome`,`created_at`,`updated_at`) VALUES (1,5.9,'Pizza buona davvero anche se americana','Almeno pomodoro e mozzarella','Pizza pepperoni',NOW(),NOW())
INSERT INTO `pizzas`(`id`, `prezzo`, `descrizione`,`ingredienti`, `nome`,`created_at`,`updated_at`) VALUES (2,12.5,'Pizza buona davvero anche se pacchiana','Almeno pomodoro e mozzarella','Cuorgherita',NOW(),NOW())
INSERT INTO `pizzas`(`id`, `prezzo`, `descrizione`,`ingredienti`, `nome`,`created_at`,`updated_at`) VALUES (3,8.9,'Pizza buona davvero anche se surgelata','Almeno pomodoro e mozzarella','Pizza surgelata',NOW(),NOW())
INSERT INTO `pizzas`(`id`, `prezzo`, `descrizione`,`ingredienti`, `nome`,`created_at`,`updated_at`) VALUES (4,8.9,'Pizza buona davvero anche se sproporzionata','Almeno pomodoro e mozzarella','Cornicione extreme',NOW(),NOW())
INSERT INTO `offerte` (`data_fine_offerta`, `data_inizio_offerta`, `id`, `pizza_id`, `nome_offerta`) VALUES ('2024-07-04', '2023-07-04', '1', '1', 'offertissima sulla pizza pepperoni ');
INSERT INTO `offerte` (`data_fine_offerta`, `data_inizio_offerta`, `id`, `pizza_id`, `nome_offerta`) VALUES ('2024-08-04', '2023-07-04', '2', '4', 'offertissima sulla pizza corniciata ');
INSERT INTO `offerte` (`data_fine_offerta`, `data_inizio_offerta`, `id`, `pizza_id`, `nome_offerta`) VALUES ('2024-07-04', '2023-07-04', '3', '2', 'offertissima sulla pizza Cuorgherita ');
INSERT INTO `offerte` (`data_fine_offerta`, `data_inizio_offerta`, `id`, `pizza_id`, `nome_offerta`) VALUES ('2023-12-04', '2023-08-04', '4', '2', 'offertissima sulla pizza Cuorgherita + patatine ');
INSERT INTO `offerte` (`data_fine_offerta`, `data_inizio_offerta`, `id`, `pizza_id`, `nome_offerta`) VALUES ('2024-09-24', '2023-09-24', '5', '2', 'offertissima sulla pizza Cuorgherita + bibita');

INSERT INTO `ruoli`(`id`, `name`) VALUES (1,'ADMIN');
INSERT INTO `ruoli`(`id`, `name`) VALUES (2,'USER');

INSERT INTO `utenti` (`id`,`first_name`, `last_name`, `email`, `password`) VALUES (1,'gionny','gionny','gionny@gionny.com','{noop}gionny');
INSERT INTO `utenti` (`id`,`first_name`, `last_name`, `email`, `password`) VALUES (2,'gionny','secondo','galletto55@gionny.com','{noop}gallo');
INSERT INTO `utenti` (`id`,`first_name`, `last_name`, `email`, `password`) VALUES (3,'gionny','terzo','mariottide@gionny.com','{noop}blabla');
INSERT INTO `utenti` (`id`,`first_name`, `last_name`, `email`, `password`) VALUES (4,'gionny','quarto','ballerina@gionny.com','{noop}blublu');

INSERT INTO `utenti_ruoli`(`user_id`, `ruoli_id`) VALUES (1,1)
INSERT INTO `utenti_ruoli`(`user_id`, `ruoli_id`) VALUES (2,2)
INSERT INTO `utenti_ruoli`(`user_id`, `ruoli_id`) VALUES (3,2)
INSERT INTO `utenti_ruoli`(`user_id`, `ruoli_id`) VALUES (4,2)