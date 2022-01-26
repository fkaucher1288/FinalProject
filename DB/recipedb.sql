-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema recipe
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `recipe` ;

-- -----------------------------------------------------
-- Schema recipe
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `recipe` DEFAULT CHARACTER SET utf8 ;
USE `recipe` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(200) NOT NULL,
  `email` VARCHAR(200) NULL,
  `password` VARCHAR(200) NOT NULL,
  `first_name` VARCHAR(100) NULL,
  `last_name` VARCHAR(100) NULL,
  `enabled` TINYINT NULL DEFAULT 1,
  `role` VARCHAR(45) NULL DEFAULT 0,
  `date_created` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `date_updated` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `image_url` VARCHAR(3000) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ingredient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ingredient` ;

CREATE TABLE IF NOT EXISTS `ingredient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `measurement_unit` VARCHAR(100) NULL,
  `brand` VARCHAR(45) NULL DEFAULT NULL,
  `category` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipe` ;

CREATE TABLE IF NOT EXISTS `recipe` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `date_created` DATE NOT NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  `creator_id` INT NOT NULL DEFAULT 1,
  `is_public` TINYINT NOT NULL DEFAULT 1,
  `prep_time` VARCHAR(45) NULL DEFAULT NULL,
  `cook_time` VARCHAR(45) NULL,
  `description` VARCHAR(1000) NULL,
  `instructions` TEXT NULL DEFAULT NULL,
  `notes` VARCHAR(1000) NULL,
  `photo_link` VARCHAR(3000) NULL DEFAULT NULL,
  `web_link` VARCHAR(3000) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_recipe_creator_idx` (`creator_id` ASC),
  CONSTRAINT `fk_recipe_creator`
    FOREIGN KEY (`creator_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipe_ingredient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipe_ingredient` ;

CREATE TABLE IF NOT EXISTS `recipe_ingredient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ingredient_id` INT NOT NULL,
  `recipe_id` INT NOT NULL,
  `quantity` DOUBLE NOT NULL,
  `remarks` VARCHAR(300) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_recipe_ingredients_recipe1_idx` (`recipe_id` ASC),
  INDEX `fk_recipe_ingredients_ingredient1_idx` (`ingredient_id` ASC),
  CONSTRAINT `fk_recipe_ingredients_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipe_ingredients_ingredient1`
    FOREIGN KEY (`ingredient_id`)
    REFERENCES `ingredient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `favorite_recipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `favorite_recipe` ;

CREATE TABLE IF NOT EXISTS `favorite_recipe` (
  `user_id` INT NOT NULL,
  `recipe_id` INT NOT NULL,
  `comment` TEXT NULL,
  `date_last_made` DATE NULL,
  `created_at` DATETIME NULL,
  INDEX `fk_user_recipe_user1_idx` (`user_id` ASC),
  INDEX `fk_user_recipe_recipe1_idx` (`recipe_id` ASC),
  PRIMARY KEY (`user_id`, `recipe_id`),
  CONSTRAINT `fk_user_recipe_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_recipe_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipe_review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipe_review` ;

CREATE TABLE IF NOT EXISTS `recipe_review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `recipe_id` INT NOT NULL,
  `created_on` DATETIME NOT NULL,
  `comment` TEXT NULL DEFAULT NULL,
  `active` TINYINT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_recipe_review_recipe1_idx` (`recipe_id` ASC),
  INDEX `fk_recipe_review_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_recipe_review_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipe_review_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dietplan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dietplan` ;

CREATE TABLE IF NOT EXISTS `dietplan` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `plan_name` VARCHAR(100) NULL,
  `description` TEXT NULL,
  `active` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_meal_plan_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_meal_plan_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dietplan_recipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dietplan_recipe` ;

CREATE TABLE IF NOT EXISTS `dietplan_recipe` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `diet_plan_id` INT NOT NULL,
  `recipe_id` INT NOT NULL,
  `sequence_number` INT NOT NULL,
  `day_name` ENUM('Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday') NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_week_recipe1_idx` (`recipe_id` ASC),
  INDEX `fk_week_meal_plan1_idx` (`diet_plan_id` ASC),
  CONSTRAINT `fk_week_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_week_meal_plan1`
    FOREIGN KEY (`diet_plan_id`)
    REFERENCES `dietplan` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dietplan_ingredient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dietplan_ingredient` ;

CREATE TABLE IF NOT EXISTS `dietplan_ingredient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `diet_plan_id` INT NOT NULL,
  `purchased` TINYINT NOT NULL DEFAULT 0,
  `ingredient_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_grocery_list_meal_plan_idx` (`diet_plan_id` ASC),
  INDEX `fk_grocery_list_ingredient1_idx` (`ingredient_id` ASC),
  CONSTRAINT `fk_grocery_list_meal_plan`
    FOREIGN KEY (`diet_plan_id`)
    REFERENCES `dietplan` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grocery_list_ingredient1`
    FOREIGN KEY (`ingredient_id`)
    REFERENCES `ingredient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category_type` ;

CREATE TABLE IF NOT EXISTS `category_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ethnicity` VARCHAR(45) NULL,
  `flavors` VARCHAR(45) NULL,
  `common alergies` VARCHAR(45) NULL,
  `lifestyle` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT(500) NULL,
  `category_type_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_category_category_type1_idx` (`category_type_id` ASC),
  CONSTRAINT `fk_category_category_type1`
    FOREIGN KEY (`category_type_id`)
    REFERENCES `category_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cookbook`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cookbook` ;

CREATE TABLE IF NOT EXISTS `cookbook` (
  `image_url` VARCHAR(8000) NULL DEFAULT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `author` VARCHAR(45) NULL,
  `description` VARCHAR(5000) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cookbook_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_cookbook_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipe_has_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipe_has_category` ;

CREATE TABLE IF NOT EXISTS `recipe_has_category` (
  `recipe_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`recipe_id`, `category_id`),
  INDEX `fk_recipe_has_category_category1_idx` (`category_id` ASC),
  INDEX `fk_recipe_has_category_recipe1_idx` (`recipe_id` ASC),
  CONSTRAINT `fk_recipe_has_category_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipe_has_category_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cookbook_has_recipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cookbook_has_recipe` ;

CREATE TABLE IF NOT EXISTS `cookbook_has_recipe` (
  `cookbook_id` INT NOT NULL,
  `recipe_id` INT NOT NULL,
  PRIMARY KEY (`cookbook_id`, `recipe_id`),
  INDEX `fk_cookbook_has_recipe_recipe1_idx` (`recipe_id` ASC),
  INDEX `fk_cookbook_has_recipe_cookbook1_idx` (`cookbook_id` ASC),
  CONSTRAINT `fk_cookbook_has_recipe_cookbook1`
    FOREIGN KEY (`cookbook_id`)
    REFERENCES `cookbook` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cookbook_has_recipe_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipe_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipe_rating` ;

CREATE TABLE IF NOT EXISTS `recipe_rating` (
  `user_id` INT NOT NULL,
  `recipe_id` INT NOT NULL,
  `rating` INT NULL,
  `create_on` DATETIME NULL,
  PRIMARY KEY (`user_id`, `recipe_id`),
  INDEX `fk_user_has_recipe_recipe1_idx` (`recipe_id` ASC),
  INDEX `fk_user_has_recipe_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_recipe_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_recipe_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipe_copy1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipe_copy1` ;

CREATE TABLE IF NOT EXISTS `recipe_copy1` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `date_created` DATE NOT NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  `creator_id` INT NOT NULL DEFAULT 1,
  `is_public` TINYINT NOT NULL DEFAULT 1,
  `prep_time` VARCHAR(45) NULL DEFAULT NULL,
  `cook_time` DOUBLE NULL,
  `description` VARCHAR(1000) NULL,
  `instructions` TEXT NULL DEFAULT NULL,
  `notes` VARCHAR(1000) NULL,
  `photo_link` VARCHAR(3000) NULL DEFAULT NULL,
  `web_link` VARCHAR(3000) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_recipe_creator_idx` (`creator_id` ASC),
  CONSTRAINT `fk_recipe_creator0`
    FOREIGN KEY (`creator_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS wolfgangpuck@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'wolfgangpuck'@'localhost' IDENTIFIED BY 'wolfgangpuck';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'wolfgangpuck'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `user` (`id`, `username`, `email`, `password`, `first_name`, `last_name`, `enabled`, `role`, `date_created`, `date_updated`, `image_url`) VALUES (1, 'wolfgangPuck', 'wolfgangPuck@gmail.com', 'wolfgangPuck', 'Wolfgang', 'Puck', 1, 'admin', '2022-01-01 00:00:00', '2022-01-01 00:00:00', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ingredient`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (1, 'Garlic', 'Clove', NULL, 'Vegetable');

COMMIT;


-- -----------------------------------------------------
-- Data for table `recipe`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `recipe` (`id`, `name`, `date_created`, `active`, `creator_id`, `is_public`, `prep_time`, `cook_time`, `description`, `instructions`, `notes`, `photo_link`, `web_link`) VALUES (1, 'Mom\'s Best Lasagna', '2022-01-01 00:00:00', 1, 1, 1, '20m', '1hr45m', 'The Best Lasagna is here! Layered with a rich meat sauce and a creamy parmesan white sauce, plus the perfect amount of mozzarella cheese! NO ricotta cheese needed!', 'Meat Sauce:\nHeat oil in a large pot over medium heat, then add in the onion and carrots and cook for 8-10 minutes, or until softened. Add in the garlic and sauté for about 1 minute, until fragrant.\nAdd beef and pork (if using) and cook while breaking it up with the end of your spoon, until browned.\nPour in the Passata, crushed tomatoes, tomato paste, crushed bouillon and dried herbs. Mix well to combine and bring to a gentle simmer. Season with desired amount of salt and pepper (I use about 3/4 teaspoon each) and sugar if needed. Cover and cook for about 20-30 minutes, occasionally mixing, until the sauce has thickened slightly and meat is tender.\nAdjust salt, pepper and dried herbs to your taste.\nParmesan White Sauce:\nIn a large pot, melt butter over medium heat. Remove from hot plate; add the flour and whisk for about 30 seconds, or until well blended.\nPlace pot back onto stove, reduce heat down to low and slowly whisk in 1 cup of the milk until well combined. Once well blended, add the remaining milk in 1 cup increments, mixing well after each addition, until all the milk is used and sauce is free from lumps. If the sauce is too thick, add a little more milk until it turns into a nice and creamy consistency.\nIncrease heat to medium and continue cooking sauce while stirring occasionally until it thickens (about 6-7 minutes) and coats the back of your wooden spoon.\nAdd in the parmesan cheese and remove from heat. Season with salt and pepper and mix until the cheese is melted through.\nTo Assemble:\nPreheat oven to 350°F | 180°F.\nSpoon about 1 cup of meat sauce on the base of a 9x13-inch baking dish, then cover with lasagna sheets. (Trim sheets to fit over the meat if needed.) Layer with 2 cups of meat sauce (or enough to cover pasta), 1 cup of white sauce and half of the mozzarella cheese. Repeat layers (leaving the remaining cheese for the top).\nPour the remaining meat sauce and white sauce over the last layer of lasagna sheets and top with the remaining mozzarella cheese. Bake for 25 minutes or until golden and bubbling.\nGarnish with parsley and let stand for about 10 minutes before slicing and serving.\nENJOY!', 'Fresh lasagna pasta sheets are found in the refrigerator section of most grocery stores. We prefer fresh pasta in our lasagna, but if you can\'t find or don\'t have access to fresh, you can use dried.  No Cook or Instant Noodles can be used without pre-boiling (check the packet instructions first). You can assemble as normal. To ensure the pasta has enough liquid to cook through while the lasagna is baking, we normally add about 1/2 cup of water to our sauce when using INSTANT. Pre Boil Or Pre Cook Pasta Sheets need to be boiled first before assembly. Follow the instructions on the packet. Add a couple of tablespoons of olive oil into the water to prevent the sheets from sticking together, and stir them occasionally with a wooden spoon. Transfer each cooked lasagna sheet carefully into a large bowl or pot filled with cool water to help stop the cooking process. Leave them in there until ready to use. This helps prevent them from sticking together or drying out.', 'https://www.thewholesomedish.com/wp-content/uploads/2018/07/Best-Lasagna-550-500x375.jpg', 'https://cafedelites.com/best-lasagna/');

COMMIT;


-- -----------------------------------------------------
-- Data for table `recipe_ingredient`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `recipe_ingredient` (`id`, `ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (1, 1, 1, 4, 'minced');

COMMIT;


-- -----------------------------------------------------
-- Data for table `favorite_recipe`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `favorite_recipe` (`user_id`, `recipe_id`, `comment`, `date_last_made`, `created_at`) VALUES (1, 1, 'Wolfgang love\'s this lasangna and want\'s to favorite it!', '2022-01-01 00:00:00', '2022-01-01 00:00:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `recipe_review`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `recipe_review` (`id`, `user_id`, `recipe_id`, `created_on`, `comment`, `active`) VALUES (1, 1, 1, '2022-01-01 00:00:00', 'This is the best lasagna!! I made it before but this time I am wanting to use fresh lasagna sheets this time. I think I have a dumb question, but do I boil them before layering according to the package instructions?? Or since they are fresh, will the sauce cook them while in the oven?? thanks so much!', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dietplan`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `dietplan` (`id`, `user_id`, `plan_name`, `description`, `active`) VALUES (1, 1, 'Wolfgang\'s Cleanse', 'get fit quick!', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dietplan_recipe`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `dietplan_recipe` (`id`, `diet_plan_id`, `recipe_id`, `sequence_number`, `day_name`) VALUES (1, 1, 1, 1, 'MONDAY');

COMMIT;


-- -----------------------------------------------------
-- Data for table `dietplan_ingredient`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `dietplan_ingredient` (`id`, `diet_plan_id`, `purchased`, `ingredient_id`) VALUES (1, 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `category_type` (`id`, `ethnicity`, `flavors`, `common alergies`, `lifestyle`) VALUES (1, 'Italian', 'Savory', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (1, 'Italian', 'Sicilian Italian', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `cookbook`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `cookbook` (`image_url`, `id`, `title`, `author`, `description`, `user_id`) VALUES ('https://www.pearson.com/store', 1, 'On Cooking: A Textbook of Culinary Fundamentals, 6th edition', 'Sarah R. Labensky', 'For over two decades, On Cooking: A Textbook of Culinary Fundamentals has prepared students for successful careers in the culinary arts. Clear and comprehensive, this best-selling text teaches the “hows” and “whys” of cooking and baking principles, while providing step-by-step instructions, visual guidance, and recipes to clarify techniques. The 6th edition expands its “fundamentals” approach, reflects key trends, and adds information on healthy cooking, sous-vide, curing, and smoking, plus dozens of new recipes and more than 200 new photographs.', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `recipe_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `recipe_rating` (`user_id`, `recipe_id`, `rating`, `create_on`) VALUES (1, 1, 5, '2022-01-01 00:00:00');

COMMIT;

