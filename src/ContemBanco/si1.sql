-- 
--
-- Estrutura da tabela `categoria`
--

CREATE TABLE IF NOT EXISTS `categoria` (
  `IDENT` int(11) NOT NULL,
  `NOME` varchar(32) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `CODIGO` int(11) NOT NULL,
  `CPF` varchar(12) DEFAULT NULL,
  `NOME` varchar(32) DEFAULT NULL,
  `EMAIL` varchar(32) DEFAULT NULL,
  `TELEFONE` int(11) DEFAULT NULL,
  `RUA` varchar(64) DEFAULT NULL,
  `NUMERO` int(11) DEFAULT NULL,
  `BAIRRO` varchar(32) DEFAULT NULL,
  `CIDADE` varchar(32) DEFAULT NULL,
  `ESTADO` varchar(2) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `compra`
--

CREATE TABLE IF NOT EXISTS `compra` (
  `COD` int(11) NOT NULL,
  `CPF_C` int(11) NOT NULL,
  `DAT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `PRECO_FINAL` float NOT NULL,
  `ISENTREGA` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE IF NOT EXISTS `produto` (
  `CODIGO` int(11) NOT NULL,
  `NOME` varchar(64) DEFAULT NULL,
  `P_CUSTO` float DEFAULT NULL,
  `P_VENDA` float DEFAULT NULL,
  `QUANTIDADE` int(11) DEFAULT NULL,
  `IDENT` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos_compra`
--

CREATE TABLE IF NOT EXISTS `produtos_compra` (
  `CODIGO` int(11) NOT NULL,
  `CODIGO_COMPRA` int(11) NOT NULL,
  `CODIGO_PRODUTO` int(11) NOT NULL,
  `QUANTIDADE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`IDENT`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`CODIGO`);

--
-- Indexes for table `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`COD`);

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`CODIGO`);

--
-- Indexes for table `produtos_compra`
--
ALTER TABLE `produtos_compra`
  ADD PRIMARY KEY (`CODIGO`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `IDENT` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `CODIGO` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `compra`
--
ALTER TABLE `compra`
  MODIFY `COD` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `CODIGO` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `produtos_compra`
--
ALTER TABLE `produtos_compra`
  MODIFY `CODIGO` int(11) NOT NULL AUTO_INCREMENT;

