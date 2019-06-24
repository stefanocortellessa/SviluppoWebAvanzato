{\rtf1\ansi\ansicpg1252\cocoartf1671\cocoasubrtf400
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\paperw11900\paperh16840\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural\partightenfactor0

\f0\fs24 \cf0 -- phpMyAdmin SQL Dump\
-- version 4.8.3\
-- https://www.phpmyadmin.net/\
--\
-- Host: localhost:8889\
-- Generation Time: Jun 24, 2019 at 09:17 AM\
-- Server version: 5.7.23\
-- PHP Version: 7.2.10\
\
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";\
SET time_zone = "+00:00";\
\
--\
-- Database: `visitaq`\
--\
\
-- --------------------------------------------------------\
\
--\
-- Table structure for table `attractions`\
--\
\
CREATE TABLE `attractions` (\
  `id` int(20) NOT NULL,\
  `name` varchar(45) DEFAULT NULL,\
  `locality` varchar(45) DEFAULT NULL,\
  `id_category` int(20) DEFAULT NULL,\
  `id_creator` int(20) DEFAULT NULL,\
  `lat` varchar(255) DEFAULT NULL,\
  `lng` varchar(255) DEFAULT NULL,\
  `description` text NOT NULL,\
  `image` varchar(255) NOT NULL\
) ENGINE=InnoDB DEFAULT CHARSET=utf8;\
\
--\
-- Dumping data for table `attractions`\
--\
\
INSERT INTO `attractions` (`id`, `name`, `locality`, `id_category`, `id_creator`, `lat`, `lng`, `description`, `image`) VALUES\
(16, 'Fontana Luminosa', 'L\\'Aquila, Viale Gran Sasso', 4, 11, '42.354012', '13.401429', 'Venne realizzata nel 1934 dallo scultore Nicola D\\'Antino a conclusione di un lungo e impegnativo progetto di sistemazione urbanistica della citt\'e0 cominciato nel 1927 e che lo port\'f2 anche alla realizzazione delle fontane gemelle di piazza Duomo. \'c8 stata sottoposta a numerosi restauri di cui l\\'ultimo successivo al terremoto dell\\'Aquila del 2009 dal quale, tuttavia, la fontana non ha subito gravi danni. \'c8 tornata fruibile al pubblico nel dicembre del 2016.La fontana \'e8 caratterizzata da due nudi femminili in bronzo sorreggenti la caratteristica conca abruzzese, posti su una vasca a pianta circolare, posta rialzata da gradini rispetto al livello della strada. \'c8 situata al centro di piazza Battaglione Alpini, all\\'ingresso di corso Vittorio Emanuele II e, quindi, del centro storico della citt\'e0; la sua funzione di propileo \'e8 accentuato dagli edifici gemelli di Palazzo del Combattente e Palazzo Leone prospicienti la fontana. Prende il nome dal suggestivo gioco di luci sull\\'acqua che si anima nelle ore notturne.La piazza e la fontana costituiscono un punto di ritrovo per gli aquilani. L\\'area \'e8 circondata solo per met\'e0 da edifici, aprendosi per un quarto al parco del castello e per il restante quarto alla zona degli impianti sportivi. Particolarmente apprezzato \'e8 il panorama che si ha dalla fontana verso il Gran Sasso.', 'fontanaluminosa.jpg'),\
(17, 'Basilica San Bernardino', 'L\\'Aquila, Via Panfilo Tedeschi', 4, 11, '42.350078', '13.402257', 'La basilica di San Bernardino \'e8 un edificio religioso dell\\'Aquila, situato nel quarto di Santa Maria. Venne costruita, con l\\'adiacente convento, fra il 1454 e il 1472 in onore di san Bernardino da Siena, le cui spoglie sono custodite all\\'interno del mausoleo del Santo realizzato a opera di Silvestro dell\\'Aquila. La facciata, eretta nel secolo successivo da Cola dell\\'Amatrice con influenze michelangiolesche, \'e8 considerata la massima espressione dell\\'architettura rinascimentale in Abruzzo. L\\'interno, in stile barocco, \'e8 dovuto alla ricostruzione dell\\'edificio in seguito al terremoto del 1703 a opera di pi\'f9 progettisti \'97 tra i quali sicuramente Filippo Barigioni, Sebastiano Cipriani e Giovan Battista Contini \'97 e conserva importanti opere d\\'arte di Andrea della Robbia, Francesco Bedeschini, Pompeo Cesura, Rinaldo Fiammingo e Donato Teodoro, oltre al gi\'e0 citato Silvestro dell\\'Aquila, autore anche del mausoleo di Maria Pereyra Camponeschi. Il soffitto in legno intagliato e ornato di oro zecchino \'e8 opera di Ferdinando Mosca. \'c8 stata inserita nell\\'elenco degli edifici monumentali nazionali nel 1902 ed elevata al rango di basilica minore \'97 titolo che condivide con le concittadine San Giuseppe Artigiano e Santa Maria di Collemaggio \'97 da papa Pio XII nel 1946. Dal 2014 il sito \'e8 gestito dal polo museale dell\\'Abruzzo. A causa del sisma del 2009 che ne ha gravemente danneggiato l\\'abside e il campanile, la basilica \'e8 stata sottoposta a lavori di riparazione e consolidamento ed \'e8 stata riaperta nel 2015.', 'sanbernardino.jpg'),\
(18, 'Basilica di Collemaggio', 'L\\'Aquila, Piazzale Collemaggio', 4, 11, '42.342844', '13.404205', 'La basilica \'e8 situata a Collemaggio, piccolo promontorio situato appena fuori le mura dell\\'Aquila, a sud-est della citt\'e0. L\\'area era posta in posizione baricentrica tra la cinta muraria e il terminale del regio tratturo L\\'Aquila-Foggia, tra i principali sentieri dell\\'epoca, con vista panoramica sul Gran Sasso d\\'Italia a sinistra ed il Monte Ocre, con il Velino-Sirente retrostante, a destra. La basilica presenta un orientamento astronomico coincidente con il giorno dell\\'Assunzione di Maria, cui l\\'edificio \'e8 dedicato. Storicamente il primo impatto con l\'92edificio avveniva dal basso e lateralmente, essendo il percorso principale verso la basilica passante per Porta Bazzano e per l\'92attuale via Caldora. Con il tempo si \'e8 potenziato invece l\'92accesso alla basilica dall\\'area di San Michele, attraverso uno squarcio nella cinta muraria ed una direttrice assiale, consolidata poi \'97 tra la fine dell\'92Ottocento e l\'92inizio del Novecento \'97 con la realizzazione del viale di Collemaggio. Tale capovolgimento dell\'92accesso, se da un lato ha appiattito e banalizzato la complessit\'e0 volumetrica della chiesa, dall\\'altro ne ha accentuato il carattere monumentale, ulteriormente marcato dalla vasta distesa verde prospiciente la facciata che rimanda alla pisana piazza dei Miracoli.Sulla parete sinistra della basilica \'e8 il portale monumentale noto come Porta Santa perch\'e9 legata alle celebrazioni della Perdonanza Celestiniana. Rimane aperta ed accessibile durante il giubileo aquilano per una sola giornata \'97 tra la sera del 28 agosto e quella del 29 \'97 ed i fedeli che l\\'attraversano ottengono l\\'indulgenza plenaria a condizione d\\'essere \'abveramente pentiti e confessati\'bb. \'c8 considerata la prima porta santa della storia, nonostante abbia assunto questo nome solo nel XV secolo ad emulazione delle porte sante romane. Il portale attuale fece la sua comparsa alla fine del Trecento (risulta, difatti, in costruzione nel 1397) e sia il Gavini che il Moretti riconoscono nella sua realizzazione una mano diversa da quella che lavor\'f2 sulla facciata, con quest\\'ultimo che ipotizza, al posto di un singolo artista, una pluralit\'e0 di maestranze d\\'origine settentrionale o addirittura francese. \'c8 caratterizzato da intagli di particolare pregio ed \'e8 sormontato dallo stemma di un\\'aquila, tra i pi\'f9 antichi e preziosi simboli della citt\'e0. La lunetta riporta l\\'affresco con la Madonna con bambino e i santi Giovanni Battista e Pietro Celestino, all\\'interno del quale viene anche mostrata la Bolla del Perdono; venne decorata, come ipotizzato dal Bologna, da Antonio Martini di Atri.', 'collemaggio.jpg'),\
(19, 'Castello di Rocca Calascio', 'Calascio (AQ)', 21, 11, '42.328846', '13.688980', 'Rocca Calascio \'e8 una rocca situata in Abruzzo, in provincia dell\\'Aquila, nel territorio del comune di Calascio, ad un\\'altitudine di 1460 metri s.l.m, poco sopra il paese. Ricompresa nel Parco nazionale del Gran Sasso e Monti della Laga, \'e8 conosciuta per la presenza del castello, tra i pi\'f9 elevati d\\'Italia, e dell\\'antico borgo medievale sottostante, ancora oggi abitato da alcuni abitanti, considerata uno dei simboli dell\\'Abruzzo. Il castello, che domina la valle del Tirino e l\\'altopiano di Navelli a poca distanza dalla piana di Campo Imperatore, \'e8 situato su un crinale a 1.460 metri d\\'altezza, in una posizione molto favorevole dal punto di vista difensivo ed era utilizzato come punto d\\'osservazione militare in comunicazione con altre torri e castelli vicini, sino all\\'Adriatico. La struttura, interamente in pietra bianca a conci squadrati, si compone di un mastio centrale di antica origine e di una cerchia muraria in pietra e quattro torri d\\'angolo a base circolare fortemente scarpate, costruite a partire dal 1480. L\\'accesso avveniva attraverso un\\'apertura sul lato orientale posta a circa cinque metri da terra, cui si accedeva attraverso una rampa in legno, originariamente retrattile, poggiata su mensole in pietra. Il castello, danneggiato dai terremoti, \'e8 stato soggetto a una serie di restauri conservativi tra il 1986 e il 1989 volti a risanare la struttura e a consentirne il recupero architettonico-funzionale, ed \'e8 oggi fruibile gratuitamente ai visitatori. Da esso si gode di un\\'ampia veduta, tra le pi\'f9 suggestive d\\'Abruzzo, con vista dei principali gruppi montuosi dell\\'Appennino abruzzese, dal Gran Sasso a nord (Corno Grande, Pizzo Cefalone, Monte Prena, Monte Camicia, Monte Bolza, Monte Ruzza), al Velino-Sirente, alla Maiella, ai Monti Marsicani, la sottostante Valle del Tirino, l\\'Altopiano di Navelli e in lontananza la Conca Peligna.', 'roccacalascio.jpg'),\
(20, 'Forte Spagnolo', 'L\\'Aquila, Via Castello', 21, 11, '42.353559', '13.405058', 'Il Forte Spagnolo, anche noto come Castello Cinquecentesco, \'e8 una fortezza dell\\'Aquila. Venne costruito nel corso di un grandioso progetto di rafforzamento militare del territorio avvenuto durante la dominazione spagnola in Italia meridionale nella prima met\'e0 del cinquecento. Mai utilizzato per scopi bellici, fu utilizzato nel Seicento come residenza del governatore spagnolo e successivamente come alloggio per i soldati francesi nell\\'Ottocento e tedeschi durante l\\'ultima guerra mondiale. Nel 1902 \'e8 stato dichiarato monumento nazionale. Restaurato nel 1951 ad opera della Soprintendenza ai Monumenti e Gallerie d\\'Abruzzo e Molise, \'e8 divenuto sede del Museo Nazionale d\\'Abruzzo, il pi\'f9 importante della regione, ed \'e8 sede di mostre e convegni. Al suo interno trovano posto anche un Auditorium e una Sala Conferenze. \'c8 rimasto gravemente danneggiato dal terremoto del 2009 e non \'e8 attualmente agibile. Dal 2011 sono ancora in corso i lavori di ricostruzione. L\\'imponente fortezza, eseguita seguendo le pi\'f9 aggiornate tecniche di fortificazione dell\\'epoca, si presenta a pianta quadrata, con ai quattro angoli massicci bastioni dai profili affilati con schema detto a punta di lancia, ognuno in direzione dei quattro punti cardinali. Nelle sue fattezze il Forte Spagnolo presenta molte analogie con il Castello di Barletta e il Castello di Copertino, anch\\'essi a pianta quadrangolare con quattro bastioni lanceolati, costruiti durante lo stesso periodo del regno di Carlo V, e, presumibilmente, per incarico dello stesso vicer\'e9 di Napoli, Pedro \'c1lvarez de Toledo, non per\'f2 dallo Escriv\'e0, ma dall\\'architetto copertinese Evangelista Menga. Il Forte \'e8 contornato da un profondo e largo fossato, mai riempito d\\'acqua, ed \'e8 accessibile da un ponte in muratura, un tempo con piano di calpestio interamente in legno parzialmente retraibile, distrutto nel 1883 e sostituito dall\\'attuale in pietra, mediante il quale si accede al Portale d\\'ingresso raffigurante lo stemma di Carlo V. La struttura \'e8 circondata da un enorme parco alberato, il Parco del Castello, autentico polmone verde della citt\'e0.', 'castello.jpg');\
\
-- --------------------------------------------------------\
\
--\
-- Table structure for table `categories`\
--\
\
CREATE TABLE `categories` (\
  `id` int(20) NOT NULL,\
  `name` varchar(45) DEFAULT NULL\
) ENGINE=InnoDB DEFAULT CHARSET=utf8;\
\
--\
-- Dumping data for table `categories`\
--\
\
INSERT INTO `categories` (`id`, `name`) VALUES\
(1, 'musei'),\
(2, 'natura'),\
(3, 'architettura'),\
(4, 'monumenti'),\
(5, 'musica'),\
(6, 'teatro'),\
(7, 'arte'),\
(8, 'cibo'),\
(9, 'spettacolo'),\
(10, 'artigianato'),\
(11, 'danza'),\
(12, 'fitness'),\
(13, 'cinema'),\
(14, 'giochi'),\
(15, 'salute'),\
(16, 'festa'),\
(17, 'religione'),\
(18, 'sport'),\
(19, 'benessere'),\
(20, 'cultura'),\
(21, 'castelli');\
\
-- --------------------------------------------------------\
\
--\
-- Table structure for table `events`\
--\
\
CREATE TABLE `events` (\
  `id` int(20) UNSIGNED NOT NULL,\
  `title` varchar(256) DEFAULT NULL,\
  `locality` varchar(256) DEFAULT NULL,\
  `startDate` datetime DEFAULT NULL,\
  `endDate` datetime DEFAULT NULL,\
  `id_category` int(20) DEFAULT NULL,\
  `id_creator` int(20) DEFAULT NULL,\
  `lat` varchar(255) DEFAULT NULL,\
  `lng` varchar(255) DEFAULT NULL,\
  `description` longtext,\
  `image` varchar(255) NOT NULL\
) ENGINE=InnoDB DEFAULT CHARSET=utf8;\
\
--\
-- Dumping data for table `events`\
--\
\
INSERT INTO `events` (`id`, `title`, `locality`, `startDate`, `endDate`, `id_category`, `id_creator`, `lat`, `lng`, `description`, `image`) VALUES\
(13, 'Un Giorno da Pastore nelle Gole del Sagittario', 'Anversa Degli Abruzzi (AQ)', '2019-06-23 11:30:00', '2019-06-26 19:30:10', 8, 11, '41.993971', '13.803686', 'L\'92attivit\'e0 si svolger\'e0 anche in caso di pioggia. Programma: 9.30 ritrovo in fattoria e colazione di benvenuto \'96 il giorno prima si riceveranno tutti i dettagli sul luogo di ritrovo 10.00 uscita con il gregge per la passeggiata nelle Gole 12.00 rientro in fattoria 12.30 ricca degustazione di formaggi di produzione propria 13.00 circa pranzo tradizionale Men\'f9 adulto: Antipasto della casa con degustazione di prodotti tipici di produzione propria, Primo a base di pasta con ricotta fresca e verdure, Agnello alla griglia, Insalata , Biscotti al vino \'96 Vino \'96 Caff\'e8. Men\'f9 per i bambini (fino a 10 anni): Formaggio fresco della casa, Pasta al sugo semplice, Frittata con patate, Biscotti, Acqua I bimbi avranno il tavolo dedicato. 15.00 laboratorio per adulti e bambini per la realizzazione del formaggio 17.30 circa fine attivit\'e0. Consegna dell\\'attestato \\'Adotta una pecora\\' L\\'attivit\'e0 si svolger\'e0 anche in caso di pioggia. Equipaggiamento necessario: zaino, scarpe comode o da trekking, abbigliamento a strati (da giacca antipioggia/antivento a t-shirt per ricambio), acqua e macchina fotografica. Costo: \'80. 35,00 adulti (tutto compreso), \'80. 15,00 bimbi da 3 a 10 anni.', 'default.jpg'),\
(14, 'Pinewood Festival', 'Coppito(AQ) - Casale Murata Gigotti, Via Ciavola 2', '2019-06-28 18:30:00', '2019-06-29 18:30:00', 9, 11, '42.368011', '13.340783', 'Pinewood Festival  ritorna: e lo fa in una terza edizione destinata a confermarlo come uno degli eventi musicali pi\'f9 importanti del centro Italia. Dopo aver portato a L\'92Aquila artisti come Coma_Cose e Pop X, l\'92organizzazione comunica le nuove proposte che affiancheranno gli artisti gi\'e0 annunciati nei due giorni di festival, dalle sorprese dell\'92ultimo periodo, come Sorrowland, M\'e9sa, Postino e Costiera, al party targato Linoleum, fino alla \'93poetica battaglia\'94 della Poetry Slam. Ecco tutta la line up del Pinewood Festival 2019 divisa per giorni: Venerd\'cc 28 Giugno Pinguini Tattici Nucleari Fast Animals And Slow Kids Populous Postino M\'c8Sa Cosmetic Laneve Blckeby Gina 001BestSabato 29 Giugno Canova Franco126 Giorgio Poi Sxrrxwland Costiera Lorenzo Bitw B2B Serena Di Linoleum Meli Merifiore Mark Bear Poetry Slam. Una line up che unisce le proposte pi\'f9 fresche che gli ultimi anni della musica italiana possano offrire, dimostrando la volont\'e0 del festival di diventare sempre pi\'f9 un appuntamento fisso nel calendario estivo delle kermesse musicali pi\'f9 attente e interessanti della penisola. Pinewood Festival si fa grande. PREVENDITE: Tickeone: http://bit.ly/PinewoodTickets DIY: http://bit.ly/PinewoodDIYSpecial price ticket abbonamento: 15,00\'80 + d.p.Prezzi Early Bird Abbonamento: 20,00\'80 + d.p.Prezzi in Prevendita Venerd\'ec 28 giugno: 16,00\'80 + d.p. Sabato 29 giugno: 16,00\'80 + d.p. Abbonamento: 25,00\'80 + d.p.Prezzi in cassa Venerd\'ec 28 giugno: 20,00\'80 Sabato 29 giugno: 20,00\'80 Abbonamento: 30,00\'80CAMPING AREA Da quest\'92anno sar\'e0 possibile vivere il Pinewood Festival a 360 gradi. Vai sul sito pinewoodfestival.eu e prenota il tuo posto tenda!EXPO AREA Arte, moda, artigianato e prodotti tipici. Nell\'92expo area di Pinewood Festival troverai questo \'e8 molto altro. Sei un espositore? Visita il nostro sito e scopri come far conoscere i tuoi prodotti al nostro pubblico!COME RAGGIUNGERCI? @ Parco Murata Gigotti Via Ciavola, Coppito, L\\'Aquila (AQ) IN AUTO: Autostrada A24 (TE - RM), uscita L\\'Aquila Ovest Da Roma (60 min) Da Pescara (60 min) Da Teramo (35 min) Da Avezzano (35 min) Da Rieti (40 min).', 'default.jpg');\
\
-- --------------------------------------------------------\
\
--\
-- Table structure for table `sessions`\
--\
\
CREATE TABLE `sessions` (\
  `id` int(11) NOT NULL,\
  `token` varchar(255) DEFAULT NULL,\
  `user_id` int(11) NOT NULL\
) ENGINE=InnoDB DEFAULT CHARSET=utf8;\
\
--\
-- Dumping data for table `sessions`\
--\
\
INSERT INTO `sessions` (`id`, `token`, `user_id`) VALUES\
(40, '686765190274980', 11),\
(42, '612155673092618', 11),\
(43, '584973493528039', 11),\
(44, '378008711000236', 11),\
(45, '207305926784033', 11),\
(46, '268712045200082', 11),\
(48, '859087330053695', 11),\
(50, '752716391659079', 11);\
\
-- --------------------------------------------------------\
\
--\
-- Table structure for table `users`\
--\
\
CREATE TABLE `users` (\
  `id` int(11) NOT NULL,\
  `name` varchar(20) DEFAULT NULL,\
  `surname` varchar(20) DEFAULT NULL,\
  `email` varchar(30) NOT NULL,\
  `password` varchar(100) NOT NULL\
) ENGINE=InnoDB DEFAULT CHARSET=utf8;\
\
--\
-- Dumping data for table `users`\
--\
\
INSERT INTO `users` (`id`, `name`, `surname`, `email`, `password`) VALUES\
(11, 'Stefano', 'Cortellessa', 'stefanocort94@gmail.com', 'b133a0c0e9bee3be20163d2ad31d6248db292aa6dcb1ee087a2aa50e0fc75ae2');\
\
--\
-- Indexes for dumped tables\
--\
\
--\
-- Indexes for table `attractions`\
--\
ALTER TABLE `attractions`\
  ADD PRIMARY KEY (`id`),\
  ADD KEY `fk_attractions_1_idx` (`id_category`),\
  ADD KEY `fk_attractions_2_idx` (`id_creator`);\
\
--\
-- Indexes for table `categories`\
--\
ALTER TABLE `categories`\
  ADD PRIMARY KEY (`id`);\
\
--\
-- Indexes for table `events`\
--\
ALTER TABLE `events`\
  ADD PRIMARY KEY (`id`),\
  ADD KEY `fk_events_1_idx` (`id_category`),\
  ADD KEY `fk_events_2_idx` (`id_creator`);\
\
--\
-- Indexes for table `sessions`\
--\
ALTER TABLE `sessions`\
  ADD PRIMARY KEY (`id`);\
\
--\
-- Indexes for table `users`\
--\
ALTER TABLE `users`\
  ADD PRIMARY KEY (`id`);\
\
--\
-- AUTO_INCREMENT for dumped tables\
--\
\
--\
-- AUTO_INCREMENT for table `attractions`\
--\
ALTER TABLE `attractions`\
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;\
\
--\
-- AUTO_INCREMENT for table `sessions`\
--\
ALTER TABLE `sessions`\
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;\
\
--\
-- AUTO_INCREMENT for table `users`\
--\
ALTER TABLE `users`\
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;\
\
--\
-- Constraints for dumped tables\
--\
\
--\
-- Constraints for table `attractions`\
--\
ALTER TABLE `attractions`\
  ADD CONSTRAINT `fk_attractions_1` FOREIGN KEY (`id_category`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,\
  ADD CONSTRAINT `fk_attractions_2` FOREIGN KEY (`id_creator`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;\
\
--\
-- Constraints for table `events`\
--\
ALTER TABLE `events`\
  ADD CONSTRAINT `fk_events_1` FOREIGN KEY (`id_category`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,\
  ADD CONSTRAINT `fk_events_2` FOREIGN KEY (`id_creator`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;\
}