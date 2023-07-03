INSERT INTO `pizzas`(`id`, `prezzo`, `descrizione`,`ingredienti`, `foto_url`, `nome`,`created_at`,`updated_at`) VALUES (1,5.9,'Pizza buona davvero anche se americana','Almeno pomodoro e mozzarella','https://st2.depositphotos.com/1692343/5636/i/600/depositphotos_56360353-stock-photo-hot-homemade-pepperoni-pizza.jpg','Pizza pepperoni',NOW(),NOW())
INSERT INTO `pizzas`(`id`, `prezzo`, `descrizione`,`ingredienti`, `foto_url`, `nome`,`created_at`,`updated_at`) VALUES (2,12.5,'Pizza buona davvero anche se pacchiana','Almeno pomodoro e mozzarella','https://www.tavolartegusto.it/wp/wp-content/uploads/2019/02/pizza-a-forma-di-cuore-Ricetta-Pizza-a-cuore.jpg','Cuorgherita',NOW(),NOW())
INSERT INTO `pizzas`(`id`, `prezzo`, `descrizione`,`ingredienti`, `foto_url`, `nome`,`created_at`,`updated_at`) VALUES (3,8.9,'Pizza buona davvero anche se surgelata','Almeno pomodoro e mozzarella','https://images.unsplash.com/photo-1594007654729-407eedc4be65?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8M3x8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=400&q=60','Pizza surgelata',NOW(),NOW())
INSERT INTO `pizzas`(`id`, `prezzo`, `descrizione`,`ingredienti`, `foto_url`, `nome`,`created_at`,`updated_at`) VALUES (4,8.9,'Pizza buona davvero anche se sproporzionata','Almeno pomodoro e mozzarella','https://media-assets.vanityfair.it/photos/61e444841e21bc3bd54b5357/1:1/w_2832,h_2832,c_limit/pizza%20tendenze.jpg','Cornicione extreme',NOW(),NOW())
INSERT INTO `offerte` (`data_fine_offerta`, `data_inizio_offerta`, `id`, `pizza_id`, `nome_offerta`) VALUES ('2024-07-04', '2023-07-04', '1', '1', 'offertissima sulla pizza pepperoni ');
INSERT INTO `offerte` (`data_fine_offerta`, `data_inizio_offerta`, `id`, `pizza_id`, `nome_offerta`) VALUES ('2024-08-04', '2023-07-04', '2', '4', 'offertissima sulla pizza corniciata ');
INSERT INTO `offerte` (`data_fine_offerta`, `data_inizio_offerta`, `id`, `pizza_id`, `nome_offerta`) VALUES ('2024-07-04', '2023-07-04', '3', '2', 'offertissima sulla pizza Cuorgherita ');
INSERT INTO `offerte` (`data_fine_offerta`, `data_inizio_offerta`, `id`, `pizza_id`, `nome_offerta`) VALUES ('2023-12-04', '2023-08-04', '4', '2', 'offertissima sulla pizza Cuorgherita + patatine ');
INSERT INTO `offerte` (`data_fine_offerta`, `data_inizio_offerta`, `id`, `pizza_id`, `nome_offerta`) VALUES ('2024-09-24', '2023-09-24', '5', '2', 'offertissima sulla pizza Cuorgherita + bibita');

INSERT INTO `ruoli`(`id`, `name`) VALUES (1,'ADMIN');
INSERT INTO `ruoli`(`id`, `name`) VALUES (2,'USER');

INSERT INTO `utenti` (`id`,`first_name`, `last_name`, `email`, `password`) VALUES (1,'gionny','gionny','gionny@gionny.com','{noop}gionny');
INSERT INTO `utenti` (`id`,`first_name`, `last_name`, `email`, `password`) VALUES (2,'gionny','secondo','galletto55@gionny.com','{noop}gallo');


INSERT INTO `utenti_ruoli`(`user_id`, `ruoli_id`) VALUES (1,1)
INSERT INTO `utenti_ruoli`(`user_id`, `ruoli_id`) VALUES (2,2)
