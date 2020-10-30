-- DROP DATABASE escola_de_musica;

CREATE DATABASE IF NOT EXISTS escola_de_musica;

USE escola_de_musica;

-- DROP TABLE tb

CREATE TABLE IF NOT EXISTS tb_usuarios(
id_usuario INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
email VARCHAR (100),
senha VARCHAR (10)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS tb_alunos(
   id_aluno INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
   nome varchar (150) NOT NULL,   
   endereco varchar (250), -- Não é unica !! [RUA | COMPLEMENTO | NUM |BAIRRO | CIDADE | UF ] (varchar 250)
   email varchar (100),
   senha varchar (10)
) ENGINE=InnoDB;

-- SELECT * FROM tb_alunos;

CREATE TABLE IF NOT EXISTS tb_cursos (
	id_curso INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nome varchar (50) NOT NULL
) ENGINE=InnoDB;

-- SELECT * FROM tb_cursos;

-- DROP TABLE tb_matriculas;

CREATE TABLE IF NOT EXISTS tb_matriculas (
	id_aluno INT UNSIGNED,
    id_curso INT UNSIGNED,
	CONSTRAINT fk_aluno FOREIGN KEY (id_aluno) REFERENCES tb_alunos(id_aluno),
    CONSTRAINT fk_curso FOREIGN KEY (id_curso) REFERENCES tb_cursos(id_curso)
) ENGINE=InnoDB;

-- SELECT * FROM tb_matriculas;
    
CREATE TABLE IF NOT EXISTS tb_professores (
	id_professor INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	nome varchar (150) NOT NULL,    
    endereco varchar (250), -- Não é unica !! [RUA | COMPLEMENTO | NUM |BAIRRO | CIDADE | UF ] (varchar 250)
    email varchar (100),
    senha varchar (10)
) ENGINE=InnoDB;
    
-- SELECT * FROM tb_professores;

-- DROP TABLE tb_professores_cursos;

CREATE TABLE IF NOT EXISTS tb_professores_cursos(
	id_professor INT UNSIGNED,
    id_curso INT UNSIGNED,    
    CONSTRAINT fk_professor1 FOREIGN KEY (id_professor) REFERENCES tb_professores(id_professor),
    CONSTRAINT fk_curso1 FOREIGN KEY (id_curso) REFERENCES tb_cursos(id_curso)
) ENGINE=InnoDB;

-- SELECT * FROM tb_professores_cursos;

CREATE TABLE IF NOT EXISTS tb_turmas (
	id_turma INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_curso INT UNSIGNED,
    id_professor INT UNSIGNED,
    nivel varchar(20), -- [BASICO | INTERMEDIARIO | AVANCADO]
    CONSTRAINT fk_professor2 FOREIGN KEY (id_professor) REFERENCES tb_professores(id_professor),
    CONSTRAINT fk_curso2 FOREIGN KEY (id_curso) REFERENCES tb_cursos(id_curso)
) ENGINE=InnoDB;

-- SELECT * FROM tb_turmas;


/*
SELECT * FROM tb_alunos;
SELECT * FROM tb_cursos;
SELECT * FROM tb_matriculas;
SELECT * FROM tb_professores;
SELECT * FROM tb_professores_cursos;
SELECT * FROM tb_turmas;
SELECT * FROM tb_usuarios;
*/

#CREATE TABLE IF NOT EXISTS tb_horario (