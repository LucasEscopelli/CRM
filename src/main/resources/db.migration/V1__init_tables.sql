CREATE TABLE cadastro (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  uuid BINARY(16) NOT NULL,
  nome VARCHAR(255) NOT NULL,
  descricao VARCHAR(255),
  data_de_nascimento BIGINT,
  atual_age INT NOT NULL,
  cpf_ou_cnpj VARCHAR(255) NOT NULL,
  email VARCHAR(255),
  observacao TEXT,
  tipo_cadastro VARCHAR(255) NOT NULL
);