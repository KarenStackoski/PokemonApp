-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Pokemon
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Pokemon
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Pokemon` DEFAULT CHARACTER SET utf8 ;
USE `Pokemon` ;

-- -----------------------------------------------------
-- Table `Pokemon`.`tb_pokemon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pokemon`.`tb_pokemon` (
  `id_pokemon` INT NOT NULL AUTO_INCREMENT,
  `pokemon` VARCHAR(50) NOT NULL,
  `tipo_pokemon` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_pokemon`),
  UNIQUE INDEX `idtb_pokemon_UNIQUE` (`id_pokemon` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pokemon`.`tb_pokemon_eletrico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pokemon`.`tb_pokemon_eletrico` (
  `id_pokemon_eletrico` INT NOT NULL,
  `pokemon_eletrico` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_pokemon_eletrico`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pokemon`.`tb_pokemon_voador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pokemon`.`tb_pokemon_voador` (
  `id_pokemon_voador` INT NOT NULL,
  `pokemon_voador` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_pokemon_voador`),
  UNIQUE INDEX `id_pokemon_voador_UNIQUE` (`id_pokemon_voador` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pokemon`.`tb_pokemon_fogo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pokemon`.`tb_pokemon_fogo` (
  `id_pokemon_fogo` INT NOT NULL,
  `pokemon_fogo` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_pokemon_fogo`),
  UNIQUE INDEX `id_pokemon_fogo_UNIQUE` (`id_pokemon_fogo` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pokemon`.`tb_pokemon_deletado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pokemon`.`tb_pokemon_deletado` (
  `id_pokemon_deletado` INT NOT NULL AUTO_INCREMENT,
  `pokemon_deletado` VARCHAR(50) NOT NULL,
  `tipo_pokemon_deletado` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_pokemon_deletado`),
  UNIQUE INDEX `id_pokemon_deletado_UNIQUE` (`id_pokemon_deletado` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pokemon`.`tb_totalizadores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pokemon`.`tb_totalizadores` (
  `id_totalizadores` INT NOT NULL AUTO_INCREMENT,
  `tot_fogo` INT NOT NULL,
  `tot_voador` INT NOT NULL,
  `tot_eletrico` INT NOT NULL,
  `tot_duplicado` INT NOT NULL,
  PRIMARY KEY (`id_totalizadores`),
  UNIQUE INDEX `id_totalizadores_UNIQUE` (`id_totalizadores` ASC) VISIBLE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
