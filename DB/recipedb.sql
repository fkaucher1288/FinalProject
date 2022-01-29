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
  `ingredient_id` INT NOT NULL,
  `recipe_id` INT NOT NULL,
  `quantity` DOUBLE NOT NULL,
  `remarks` VARCHAR(300) NULL,
  INDEX `fk_recipe_ingredients_recipe1_idx` (`recipe_id` ASC),
  INDEX `fk_recipe_ingredients_ingredient1_idx` (`ingredient_id` ASC),
  PRIMARY KEY (`ingredient_id`, `recipe_id`),
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
  `diet_plan_id` INT NOT NULL,
  `recipe_id` INT NOT NULL,
  `sequence_number` INT NOT NULL,
  `day_name` ENUM('Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday') NULL,
  INDEX `fk_week_recipe1_idx` (`recipe_id` ASC),
  INDEX `fk_week_meal_plan1_idx` (`diet_plan_id` ASC),
  PRIMARY KEY (`diet_plan_id`, `recipe_id`),
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
  `diet_plan_id` INT NOT NULL,
  `purchased` TINYINT NOT NULL DEFAULT 0,
  `ingredient_id` INT NOT NULL,
  INDEX `fk_grocery_list_meal_plan_idx` (`diet_plan_id` ASC),
  INDEX `fk_grocery_list_ingredient1_idx` (`ingredient_id` ASC),
  PRIMARY KEY (`diet_plan_id`, `ingredient_id`),
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
  `name` VARCHAR(1000) NULL,
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
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `title` VARCHAR(100) NULL,
  `author` VARCHAR(45) NULL,
  `description` VARCHAR(5000) NULL,
  `image_url` VARCHAR(8000) NULL DEFAULT NULL,
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
  `created_on` DATETIME NULL,
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
-- Table `recipe_review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipe_review` ;

CREATE TABLE IF NOT EXISTS `recipe_review` (
  `user_id` INT NOT NULL,
  `recipe_id` INT NOT NULL,
  `created_on` DATETIME NOT NULL,
  `comment` TEXT NULL DEFAULT NULL,
  `active` TINYINT NULL,
  PRIMARY KEY (`user_id`, `recipe_id`),
  INDEX `fk_user_has_recipe_recipe2_idx` (`recipe_id` ASC),
  INDEX `fk_user_has_recipe_user2_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_recipe_user2`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_recipe_recipe2`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `recipe` (`id`)
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
INSERT INTO `user` (`id`, `username`, `email`, `password`, `first_name`, `last_name`, `enabled`, `role`, `date_created`, `date_updated`, `image_url`) VALUES (1, 'wolfgangPuck', 'wolfgangPuck@gmail.com', '$2a$10$Ofg7vwo1bEe09oxtQDA2geVpiibBWkcPZZpoRBuSOI0fId.MRzOBa', 'Wolfgang', 'Puck', 1, 'admin', '2022-01-01 00:00:00', '2022-01-01 00:00:00', NULL);
INSERT INTO `user` (`id`, `username`, `email`, `password`, `first_name`, `last_name`, `enabled`, `role`, `date_created`, `date_updated`, `image_url`) VALUES (2, 'marthaStewart', 'marthaStewart@gmail.com', 'marthaStewart', 'Martha', 'Stewart', 1, 'user', '2022-01-27 00:00:00', '2022-01-27 00:00:00', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ingredient`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (1, 'Garlic', 'Clove', NULL, 'Vegetable');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (2, 'Butter', 'Tablespoon', '', 'Diary');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (3, 'Garlic', 'Clove', '', 'Vegetables');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (4, 'Cheddar Cheese', 'Cup', '', 'Dairy');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (5, 'Parsley', 'Bunch', '', 'Vegetables');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (6, 'Bread', 'Slice', '', 'Grain');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (7, 'Apples', 'Ounce', '', 'Fruit');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (8, 'Chicken Thighs', 'Pounds', '', 'Meat');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (9, 'Honey', 'Tablespoon', '', 'Sugar');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (10, 'Bacon', 'Ounce', '', 'Meat');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (11, 'Egg', 'Unit', '', 'Dairy');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (12, 'Lemon Juice', 'Cup', '', 'Fruit');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (13, 'Suger', 'Tablespoon', '', 'Sweets');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (14, 'Water', 'Cup', '', 'Liquid');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (15, 'Lemon juice', 'Cup', '', 'Liquid');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (16, 'Yogurt', 'Cup', '', 'Dairy');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (17, 'Olive Oil', 'Tablespoon', '', 'Oils');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (18, 'Red pepper flakes', 'Teaspoon', '', 'Seasoning');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (19, 'Salt', 'Teaspoon', '', 'Seasoning');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (20, 'Crushed Tomatoes', 'Ounce', '', 'Vegetables');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (21, 'Marinara Sauce', 'Ounce', '', 'Sauce');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (22, 'Spaghetti', 'Ounce', '', 'Pasta');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (23, 'Flaky Pie Crust', 'Unit', '', 'Pie Crust');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (25, 'Whole Milk', 'Cup', '', 'Dairy');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (26, 'Heavy Cream', 'Cup', '', 'Dairy');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (27, 'Pepper', 'Teaspoon', '', 'Seasoning');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (28, 'Feta Cheese', 'Cup', '', 'Dairy');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (29, 'Salmon', 'Ounce', '', 'Fish');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (30, 'Smoked Paprika', 'Teaspoon', '', 'Seasoning');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (31, 'Blackening Seasoning', 'Teaspoon', '', 'Seasoning');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (32, 'Soy Sauce', 'Tablespoon', '', 'Sauce');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (33, 'Sriracha Sauce', 'Tablespoon', '', 'Sauce');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (34, 'Beef Chuck', 'Pounds', '', 'Meat');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (35, 'Onion', 'Cup', '', 'Vegetables');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (36, 'Carrot', 'Cup', '', 'Vegetables');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (37, 'Celery', 'Cup', '', 'Vegetables');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (39, 'Tomato Paste', 'Tablespoon', '', 'Vegetables');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (40, 'Beef Bouillon', 'Tablespoon', '', 'Seasoning');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (41, 'Red Wine', 'Cup', '', 'Liquid');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (42, 'Fresh Time', 'Sprigs', '', 'Seasoning');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (43, 'Bay Leaves', 'Leaves', '', 'Seasoning');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (44, 'Pappardelle', 'Ounces', '', 'Pasta');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (45, 'Paremsan', 'Cup', '', 'Dairy');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (46, 'Vegetable Oil', 'Tablespoon', '', 'Oils');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (47, 'Cornstarch', 'Tablespoon', '', 'Seasoning');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (48, 'Flour', 'Tablespoon', '', 'Seasoning');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (49, 'Garlic Salt', 'Tablespoon', '', 'Seasoning');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (50, 'Paprika', 'Tablespoon', '', 'Seasoning');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (51, 'Chicken Breast', 'Pounds', '', 'Meat');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (52, 'Sesame Oil', 'Tablespoon', '', 'Oils');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (53, 'Rice Vinegar', 'Tablespoon', '', 'Liquid');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (54, 'Sweet Chili Sauce', 'Tablespoon', '', 'Sauce');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (55, 'Ketchup', 'Tablespoon', '', 'Sauce');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (56, 'Brown Sugar', 'Tablespoon', '', 'Seasoning');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (57, 'Rice Noodles', 'Ounce', '', 'Pasta');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (58, 'Shrimp', 'Ounce', '', 'Fish');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (59, 'Bean Sprouts', 'Cup', '', 'Vegetables');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (60, 'Red Bell Pepper', 'Cup', '', 'Vegetables');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (61, 'Green Onions', 'Cup', '', 'Vegetables');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (62, 'Peanuts', 'Cup', '', 'Legume');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (63, 'Lime Juice', 'Cup', '', 'Liquid');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (64, 'Ciltantro', 'Cup', '', 'Vegetables');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (65, 'Fish Sauce', 'Tablespoon', '', 'Sauce');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (66, 'Peanut Butter', 'Tablespoon', '', 'Sauce');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (67, 'Granulated Suger', 'Teaspoon', '', 'Seasoning');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (68, 'Vanilla Extract', 'Tablespoon', '', 'Seasoning');
INSERT INTO `ingredient` (`id`, `name`, `measurement_unit`, `brand`, `category`) VALUES (69, 'Lemon Zest', 'Tablespoon', '', 'Seasoning');

COMMIT;


-- -----------------------------------------------------
-- Data for table `recipe`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `recipe` (`id`, `name`, `date_created`, `active`, `creator_id`, `is_public`, `prep_time`, `cook_time`, `description`, `instructions`, `notes`, `photo_link`, `web_link`) VALUES (1, 'Mom\'s Best Lasagna', '2022-01-01 00:00:00', 1, 1, 1, '20m', '1hr45m', 'The Best Lasagna is here! Layered with a rich meat sauce and a creamy parmesan white sauce, plus the perfect amount of mozzarella cheese! NO ricotta cheese needed!', 'Meat Sauce:\nHeat oil in a large pot over medium heat, then add in the onion and carrots and cook for 8-10 minutes, or until softened. Add in the garlic and sauté for about 1 minute, until fragrant.\nAdd beef and pork (if using) and cook while breaking it up with the end of your spoon, until browned.\nPour in the Passata, crushed tomatoes, tomato paste, crushed bouillon and dried herbs. Mix well to combine and bring to a gentle simmer. Season with desired amount of salt and pepper (I use about 3/4 teaspoon each) and sugar if needed. Cover and cook for about 20-30 minutes, occasionally mixing, until the sauce has thickened slightly and meat is tender.\nAdjust salt, pepper and dried herbs to your taste.\nParmesan White Sauce:\nIn a large pot, melt butter over medium heat. Remove from hot plate; add the flour and whisk for about 30 seconds, or until well blended.\nPlace pot back onto stove, reduce heat down to low and slowly whisk in 1 cup of the milk until well combined. Once well blended, add the remaining milk in 1 cup increments, mixing well after each addition, until all the milk is used and sauce is free from lumps. If the sauce is too thick, add a little more milk until it turns into a nice and creamy consistency.\nIncrease heat to medium and continue cooking sauce while stirring occasionally until it thickens (about 6-7 minutes) and coats the back of your wooden spoon.\nAdd in the parmesan cheese and remove from heat. Season with salt and pepper and mix until the cheese is melted through.\nTo Assemble:\nPreheat oven to 350°F | 180°F.\nSpoon about 1 cup of meat sauce on the base of a 9x13-inch baking dish, then cover with lasagna sheets. (Trim sheets to fit over the meat if needed.) Layer with 2 cups of meat sauce (or enough to cover pasta), 1 cup of white sauce and half of the mozzarella cheese. Repeat layers (leaving the remaining cheese for the top).\nPour the remaining meat sauce and white sauce over the last layer of lasagna sheets and top with the remaining mozzarella cheese. Bake for 25 minutes or until golden and bubbling.\nGarnish with parsley and let stand for about 10 minutes before slicing and serving.\nENJOY!', 'Fresh lasagna pasta sheets are found in the refrigerator section of most grocery stores. We prefer fresh pasta in our lasagna, but if you can\'t find or don\'t have access to fresh, you can use dried.  No Cook or Instant Noodles can be used without pre-boiling (check the packet instructions first). You can assemble as normal. To ensure the pasta has enough liquid to cook through while the lasagna is baking, we normally add about 1/2 cup of water to our sauce when using INSTANT. Pre Boil Or Pre Cook Pasta Sheets need to be boiled first before assembly. Follow the instructions on the packet. Add a couple of tablespoons of olive oil into the water to prevent the sheets from sticking together, and stir them occasionally with a wooden spoon. Transfer each cooked lasagna sheet carefully into a large bowl or pot filled with cool water to help stop the cooking process. Leave them in there until ready to use. This helps prevent them from sticking together or drying out.', 'https://www.thewholesomedish.com/wp-content/uploads/2018/07/Best-Lasagna-550-500x375.jpg', 'https://cafedelites.com/best-lasagna/');
INSERT INTO `recipe` (`id`, `name`, `date_created`, `active`, `creator_id`, `is_public`, `prep_time`, `cook_time`, `description`, `instructions`, `notes`, `photo_link`, `web_link`) VALUES (2, 'Crock pot apple chicken', '2022-01-01 00:00:00', 1, 1, 1, '45m', '8h', '', 'Combine barbecue sauce, grated apple and lemon juice in a small bowl; mix well.\nWrap 2 pieces of bacon around each chicken breast. Place chicken breasts in base of crock pot\nPour barbecue sauce mixture over top of chicken, along with a couple apple slices (optional)\nCover; cook on Low for 8 hours.\nRemove chicken from crock pot.', '', 'https://myfridgefood.com/media/7614/baconwrappedpineapplechicken.jpg', '');
INSERT INTO `recipe` (`id`, `name`, `date_created`, `active`, `creator_id`, `is_public`, `prep_time`, `cook_time`, `description`, `instructions`, `notes`, `photo_link`, `web_link`) VALUES (3, 'Grilled cheese on garlic bread', '2022-01-15 00:00:00', 1, 1, 1, '5m', '10m', '', 'Heat large skillet over medium heat. Combine butter, garlic, Pecorino Romano and parsley in small bowl, stirring to combine. \nSpread the softened garlic butter on one side of 4 slices of bread. Place 2 slices of cheese in between 2 slices of bread, with the butter on the outside. \nPlace the sandwiches in the skillet. Cook until golden brown on one side, about 3-4 minutes. Flip and cook until golden brown on the second side and cheese has melted, another 3-4 minutes. Cut sandwiches in half and serve.', '', 'https://myfridgefood.com/media/8978/gbgrilledchssm.jpg', '');
INSERT INTO `recipe` (`id`, `name`, `date_created`, `active`, `creator_id`, `is_public`, `prep_time`, `cook_time`, `description`, `instructions`, `notes`, `photo_link`, `web_link`) VALUES (4, 'Mashed potato muffins', '2022-01-01 00:00:00', 1, 1, 1, '20m', '45m', '', 'Just mash potatoes plain with butter or you can add yummy ingredients like cooked bacon, cheese, parsley, green onion, garlic, etc. Stuff in to a greased muffin tin, run a fork along the top and brush with melted butter or olive oil. Bake at 375 degrees or until tops are crispy and golden', '', 'https://myfridgefood.com/Media/Recipe/549141_10151318672016266_1631096513_n.jpg', '');
INSERT INTO `recipe` (`id`, `name`, `date_created`, `active`, `creator_id`, `is_public`, `prep_time`, `cook_time`, `description`, `instructions`, `notes`, `photo_link`, `web_link`) VALUES (5, 'Lemon sorbet', '2022-01-15 00:00:00', 1, 2, 1, '45m', '5h', '', 'In a small saucepan, bring sugar and the water to boiling, stirring to dissolve sugar. Stir in the 1 tablespoon lemon zest and the lemon juice. Pour lemon mixture into the bowl of a 1-quart ice cream maker. Freeze according to ice cream maker\'s instructions (approximately 25-30 minutes). Transfer to a container; ripen in freezer for 4 hours. Let stand at room temperature for 5 minutes before serving', '', 'https://www.simplyrecipes.com/thmb/GtZyMvsn0XUu-8A9omEh0RXKs24=/735x0/__opt__aboutcom__coeus__resources__content_migration__simply_recipes__uploads__2007__01__meyer-lemon-sorbet-vertical-a-1300-1350fefc9ca544adaa9dbf6cea4370d7.jpg', '');
INSERT INTO `recipe` (`id`, `name`, `date_created`, `active`, `creator_id`, `is_public`, `prep_time`, `cook_time`, `description`, `instructions`, `notes`, `photo_link`, `web_link`) VALUES (6, 'Easy baked spaghetti', '2022-01-01 00:00:00', 1, 1, 1, '10m', '1h', '', 'Noodles:\nPreheat oven to 350˚.\nCook noodles according to package instructions, stopping a minute or two before al dente.\nDrain the noodles and mix with the spaghetti sauce and a cup of cheese.\nPlace into a 9×13 baking dish and top with the remaining cup of cheese.\nBake for 20 minutes, or until cheese is golden and bubbly.\nSauce:\nIn a medium sauce pan, combine the olive oil, garlic, and red pepper flakes and sauté over medium-high heat until the garlic sizzles.\nStir in the tomatoes and simmer for 5-10 minutes, until the sauce starts to thicken.\nRemove from heat and stir in lemon juice and salt.', '', 'https://myfridgefood.com/media/7428/bakedspaghetti.jpg', '');
INSERT INTO `recipe` (`id`, `name`, `date_created`, `active`, `creator_id`, `is_public`, `prep_time`, `cook_time`, `description`, `instructions`, `notes`, `photo_link`, `web_link`) VALUES (7, 'Honey Glazed Salmon', '2022-01-01 00:00:00', 1, 1, 1, '8m', '10m', 'Sticky sweet and garlicky, this glazed salmon recipe comes together in just 20 minutes', 'Pat salmon dry, then season with salt, pepper, paprika and blackening seasoning (if using). Set aside. Adjust oven rack to middle position, then preheat broiler. Add butter and oil to a large, oven-safe skillet over MED-HIGH heat. Once butter is melted, add garlic, water, soy sauce, sriracha, honey and lemon juice and cook 30 seconds or so, until sauce is heated through. Add salmon, skin side down (if using salmon with skin), and cook 3 minutes. While salmon cooks, baste frequently with sauce from the pan by spooning it over the top of the salmon. Broil salmon for 5-6 minutes, basting with sauce once during the broil, until salmon is caramelized and cooked to desired doneness. Garnish with minced parsley if desired.  \n', 'If you\'d prefer, season salmon as directed, then pan sear in the pan.  Flip over and sear on the other side, then remove to a plate.  Add sauce ingredients to pan and cook until warmed through.  Add salmon back to pan and spoon sauce over the salmon.\n', 'https://www.thechunkychef.com/wp-content/uploads/2020/01/Honey-Garlic-Salmon-fork-320x320.jpg', '');
INSERT INTO `recipe` (`id`, `name`, `date_created`, `active`, `creator_id`, `is_public`, `prep_time`, `cook_time`, `description`, `instructions`, `notes`, `photo_link`, `web_link`) VALUES (8, 'Quiche', '2022-01-01 00:00:00', 1, 1, 1, '30m', '1h', 'This is a perfect base quiche recipe and it’s all baked in a super flaky homemade pie crust. Use a combination of milk and heavy cream for the richest, creamiest filling and add your favorites such as bacon, feta cheese, ham, white cheddar cheese, crab meat or spinach.', 'Prepare pie crust: I like to make sure my pie dough is prepared before I begin the quiche. Make pie dough the night before because it needs to chill in the refrigerator for at least 2 hours before rolling out and blind baking (next step).\nRoll out the chilled pie dough: On a floured work surface, roll out one of the disks of chilled dough (use the 2nd pie crust for another recipe). Turn the dough about a quarter turn after every few rolls until you have a circle 12 inches in diameter. Carefully place the dough into a 9-inch pie dish. Tuck it in with your fingers, making sure it is completely smooth. To make a lovely edge, I do not trim excess dough around the edges. Instead, fold the excess dough back over the edge and use your hands to mold the edge into a rim around the pie. Crimp the edges with a fork or use your fingers to flute the edges. Chill the pie crust in the refrigerator for at least 30 minutes and up to 5 days. Cover the pie crust with plastic wrap if chilling for longer than 30 minutes. While the crust is chilling, preheat oven to 375°F (190°C). Partially blind bake: Line the chilled pie crust with parchment paper. Fill with pie weights or dried beans. Make sure the weights are evenly distributed around the pie dish. Bake until the edges of the crust are starting to brown, about 15-16 minutes. Remove pie from the oven and carefully lift the parchment paper (with the weights) out of the pie. Prick holes all around the bottom crust with a fork. Return the pie crust to the oven. Bake until the bottom crust is just beginning to brown, about 7-8 minutes. Remove from the oven and set aside. (Crust can still be warm when you pour in the filling. You can partially pre-bake the crust up to 3 days ahead of time. Cover cooled crust tightly and refrigerate until ready to fill.)\nReduce oven temperature to 350°F (177°C).\nIn a large bowl with a handheld or stand mixer fitted with a whisk attachment, beat the eggs, whole milk, heavy cream, salt, and pepper together on high speed until completely combined, about 1 minute. Whisk in add-ins and then pour into crust.\nBake the quiche until the center is just about set, about 45-55 minutes. Don’t over-bake. Use a pie crust shield to prevent the pie crust edges from over-browning. Allow to cool for 15 minutes. Top with optional toppings before slicing and serving, if desired. Or you can cool the quiche completely before serving– it’s fantastic at room temperature!\nThis quiche makes great leftovers! Cover tightly and store in the refrigerator for up to 4 days.\n', 'Make Ahead & Freezing Instructions: The pie dough can be prepared ahead of time and stored in the refrigerator for up to 5 days or in the freezer for up to 3 months. You can pre-bake the crust ahead of time too. See end of step 2. To freeze, cool baked quiche completely, then cover tightly with a couple sheets of aluminum foil and freeze for up to 3 months. Thaw in the refrigerator or on the counter, then bake at 350°F (177°C) for 20-25 minutes.', 'https://sallysbakingaddiction.com/quiche-recipe/print/70031/#respond', '');
INSERT INTO `recipe` (`id`, `name`, `date_created`, `active`, `creator_id`, `is_public`, `prep_time`, `cook_time`, `description`, `instructions`, `notes`, `photo_link`, `web_link`) VALUES (9, 'Slow Cooked Shredded Beef Ragu Pasta', '2022-01-01 00:00:00', 1, 2, 1, '20m', '2h 30m', 'Ragu is one of those recipes that really showcases the beauty of Italian cooking - everyday ingredients, fast prep, leave it to cook long and slow and you end up with a luscious dish that tastes like a million bucks. This recipe makes enough sauce to serve 8 and freezes great.', 'Pat beef dry and sprinkle with salt and pepper Sear Beef: Heat 1 tbsp olive oil over high heat in a heavy based pot. Add beef and sear each piece aggressively on all sides until very browned (3 - 5 minutes in total), then remove onto a plate. Turn stove down to medium low and add remaining 2 tbsp of olive oil. Soffrito: Add garlic and onion and sauté for 2 minutes. Then add the carrots and celery and sauté slowly for 5 minutes. Add remaining Ragu ingredients and return the beef to the pot (including pooled juices). Turn the stove up and bring it to a simmer, then turn it down to low so it\'s bubbling very very gently. (Note 7) Slow cook: Cover the pot and let it cook for 2 hours or until beef is tender enough to shred. (Note 5 for slow cooker and pressure cooker). Shred: Remove beef then coarsely shred with 2 forks. Return beef to the pot. Simmer for 30 minutes until sauce is reduced and thickened - beef will soften slightly more during this step. Final season: Do a taste test and adjust the seasoning to your taste with salt and pepper. ALSO, add 1/2 tsp sugar if sauce is a bit sour for your taste (Note 6). Place the lid on and set aside until ready to serve (it\'s even better the next day and freezes well for months!).  \n', 'Beef - Cut the beef into 4 pieces that are around the size of a baseball. The cook time of this recipe assumes you do this. \nCelery and carrots sautéed with the onions and garlic is called \"soffritto\" in Italian cooking. It is a very traditional base for many Italian dishes. Cooking them slowly over low heat releases their flavour and adds an extra dimension to this dish. But it\'s not a deal killer if you skip these ingredients.\nBeef stock - You could use liquid beef stock instead of water + stock cubes.\n', 'https://www.recipetineats.com/wp-content/uploads/2017/07/Beef-Ragu-11a.jpg', '');
INSERT INTO `recipe` (`id`, `name`, `date_created`, `active`, `creator_id`, `is_public`, `prep_time`, `cook_time`, `description`, `instructions`, `notes`, `photo_link`, `web_link`) VALUES (10, 'Crispy Sesame Chicken with a Sticky Asian Sauce\n', '2022-01-01 00:00:00', 1, 2, 1, '15m', '15m', 'Crispy Sesame Chicken with a Sticky Asian Sauce - tastier then that naughty takeaway and super simple to make. Sweet, salty, crispy, sticky and a little bit spicy - it covers all the bases for one of these meals that everyone polishes off. Its a real family favorite!', 'Heat the oil in a wok or large frying pan until very hot. Whilst the oil is heating, place the egg in one shallow bowl and the cornflour in another shallow bowl. Add the flour, salt, pepper, garlic salt and paprika to another shallow bowl and mix together. Dredge the chicken in the cornflour, then dip in the egg (make sure all of the chicken is covered in egg wash), and finally dredge it in the seasoned flour. Add to the wok and cook on a high heat for 6-7 minutes, turning two or three times during cooking, until well browned. You may need to cook in two batches (I find I can do it in one batch so long as it\'s no more than 3 chicken breasts). Remove from the pan and place in a bowl lined with kitchen towels. Add all of the sauce ingredients to the hot wok, stir and bubble on a high heat until the sauce reduces by about a third (should take 2-3 minutes). Add the chicken back in and toss in the sauce to coat. Cook for 1-2 minutes. Turn off the heat and divide between four bowls. Serve with boiled rice and top with sesame seeds and spring onions. \n', 'Double these ingredients if you want extra sauce, rather than just coating the chicken.', 'https://www.kitchensanctuary.com/wp-content/uploads/2016/06/Crispy-Sesame-Chicken-square-FS-300x300.jpg', '');
INSERT INTO `recipe` (`id`, `name`, `date_created`, `active`, `creator_id`, `is_public`, `prep_time`, `cook_time`, `description`, `instructions`, `notes`, `photo_link`, `web_link`) VALUES (11, 'Pad Thai\n\n', '2022-01-01 00:00:00', 1, 2, 1, '15m', '15m', 'This amazing Pad Thai recipe is easy, approachable and can be made in under 30 minutes, fresh ingredients and a delicious homemade pad thai sauce.', 'Cook noodles according to package instructions, just until tender.  Rinse under cold water. Mix the sauce ingredients together. Set aside. Heat 1½ tablespoons of oil in a large saucepan or wok over medium-high heat. Add the shrimp, chicken or tofu, garlic and bell pepper. The shrimp will cook quickly, about 1-2 minutes on each side, or until pink. If using chicken, cook until just cooked through, about 3-4 minutes, flipping only once. Push everything to the side of the pan. Add a little more oil and add the beaten eggs. Scramble the eggs, breaking them into small pieces with a spatula as they cook. Add noodles, sauce, bean sprouts and peanuts to the pan (reserving some peanuts for topping at the end). Toss everything to combine. Top with green onions, extra peanuts, cilantro and lime wedges. Serve immediately!  \n', 'To use tamarind paste in the sauce, substitute 2 Tablespoons in place of the vinegar.', 'https://tastesbetterfromscratch.com/wp-content/uploads/2018/07/Pad-Thai-8-500x500.jpg', '');
INSERT INTO `recipe` (`id`, `name`, `date_created`, `active`, `creator_id`, `is_public`, `prep_time`, `cook_time`, `description`, `instructions`, `notes`, `photo_link`, `web_link`) VALUES (12, 'The Best (and Easiest) Ice Cream You\'ll Ever Make', '2022-01-01 00:00:00', 1, 1, 1, '5m', '5m', 'Rich and creamy homemade vanilla ice cream recipe that only requires five ingredients!', 'Pour 1 cup of the cream into a saucepan and add the sugar, salt. Scrape the seeds of the vanilla bean into the pot and then add the vanilla pod to the pot. Warm the mixture over medium heat, just until the sugar dissolves. Remove from the heat and add the remaining cream, milk, and vanilla extract (if using extract). Stir to combine and chill in the refrigerator. When ready to churn, remove the vanilla pod, whisk mixture again and pour into ice cream maker. Churn according to the manufacturer\'s instructions. Transfer the finished ice cream to an airtight container and place in the freezer until ready to serve. Enjoy!  \n', 'Feel free to skip the simmer step and simply whisk everything together, then pour directly into the ice cream maker. The ice cream is absolutely delicious this way, as well as when simmered and chilled first.', 'https://barefeetinthekitchen.com/wp-content/uploads/2018/05/Easiest-Ice-Cream-1-1-of-1-600x600.jpg', '');
INSERT INTO `recipe` (`id`, `name`, `date_created`, `active`, `creator_id`, `is_public`, `prep_time`, `cook_time`, `description`, `instructions`, `notes`, `photo_link`, `web_link`) VALUES (13, 'Lemon Sorbet', '2022-01-01 00:00:00', 1, 2, 1, '15m', '20m', 'Whether you serve it in chilled bowls or scoop it into cut lemon halves, this creamy four-ingredient sorbet is both sweet and tart. It makes a delightfully refreshing finish to any meal.', '1. Place sugar and water in a small saucepan; bring to a boil over medium heat, stirring constantly. Reduce heat; simmer, uncovered, 2 minutes. Cool completely.\n2. Stir in lemon zest and juice. Fill cylinder of ice cream maker no more than two-thirds full; freeze according to manufacturer\'s directions.\n3. Transfer sorbet to a freezer container, allowing headspace for expansion. Freeze until firm, about 4 hours. If desired, serve in cookie cups with lemon wedges and top with additional lemon zest.\n', '', 'https://www.tasteofhome.com/wp-content/uploads/2018/01/Lemon-Sorbet_EXPS_QEBZ20_25719_E01_28_2b-1.jpg?w=1200', '');

COMMIT;


-- -----------------------------------------------------
-- Data for table `recipe_ingredient`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (1, 1, 4, 'Minced');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (2, 1, 6, 'Melted');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (3, 2, 5, 'Shredded');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (4, 1, 1, 'Chopped');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (5, 1, 1, 'Crumbled');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (6, 2, 8, 'Chopped');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (1, 2, 8, 'minced');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (2, 2, 5, 'Melted');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (3, 1, 4, 'Shredded');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (23, 8, 1, 'Flaky');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (11, 8, 4, 'Whisked');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (25, 8, 1, 'Whole');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (26, 8, 1, 'Whipped');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (19, 8, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (28, 8, 1, 'Shredded');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (27, 8, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (19, 7, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (27, 7, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (29, 7, 4, 'Filets');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (2, 7, 3, 'Melted');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (17, 7, 2, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (1, 7, 6, 'Minced');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (9, 7, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (14, 7, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (32, 7, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (33, 7, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (15, 7, 2, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (34, 9, 3, 'Shredded');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (19, 9, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (27, 9, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (17, 9, 3, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (3, 9, 3, 'Minced');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (35, 9, 1, 'Diced');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (36, 9, 1, 'Diced');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (37, 9, 1, 'Diced');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (20, 9, 28, 'Include Juices');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (39, 9, 3, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (40, 9, 3, 'Crushed');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (41, 9, 1, 'Dry');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (14, 9, 2, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (42, 9, 2, 'Chopped');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (43, 9, 3, 'Whole');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (44, 9, 28, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (45, 9, 1, 'Grated');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (46, 10, 4, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (11, 10, 2, 'Lightly Beaten');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (47, 10, 3, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (48, 10, 10, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (19, 10, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (27, 10, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (49, 10, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (50, 10, 2, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (51, 10, 3, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (52, 10, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (3, 10, 2, 'Minced');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (53, 10, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (9, 10, 2, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (54, 10, 2, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (55, 10, 3, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (56, 10, 2, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (32, 10, 4, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (57, 11, 8, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (46, 11, 3, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (3, 11, 3, 'Minced');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (58, 11, 8, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (11, 11, 2, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (59, 11, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (60, 11, 1, 'Thinly Sliced');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (61, 11, 3, 'Chopped');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (62, 11, 2, 'Juiced');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (63, 11, 1, 'Dry Roasted');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (64, 11, 1, 'Chopped');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (65, 11, 3, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (32, 11, 1, 'Low-sodium');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (56, 11, 5, 'Light');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (53, 11, 2, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (33, 11, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (66, 11, 2, 'Optional');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (26, 12, 2, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (25, 12, 2, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (67, 12, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (19, 12, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (68, 12, 2, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (67, 13, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (14, 13, 1, '');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (69, 13, 3, 'Zest');
INSERT INTO `recipe_ingredient` (`ingredient_id`, `recipe_id`, `quantity`, `remarks`) VALUES (12, 13, 1, '');

COMMIT;


-- -----------------------------------------------------
-- Data for table `favorite_recipe`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `favorite_recipe` (`user_id`, `recipe_id`, `comment`, `date_last_made`, `created_at`) VALUES (1, 1, 'Wolfgang love\'s this lasangna and want\'s to favorite it!', '2022-01-01 00:00:00', '2022-01-01 00:00:00');

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
INSERT INTO `dietplan_recipe` (`diet_plan_id`, `recipe_id`, `sequence_number`, `day_name`) VALUES (1, 1, 1, 'Monday');

COMMIT;


-- -----------------------------------------------------
-- Data for table `dietplan_ingredient`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `dietplan_ingredient` (`diet_plan_id`, `purchased`, `ingredient_id`) VALUES (1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `category_type` (`id`, `name`) VALUES (1, 'ethnic');
INSERT INTO `category_type` (`id`, `name`) VALUES (2, 'flavor');
INSERT INTO `category_type` (`id`, `name`) VALUES (3, 'allergies');
INSERT INTO `category_type` (`id`, `name`) VALUES (4, 'lifestyle');

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (1, 'Italian', 'Sicilian Italian', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (2, 'Albanian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (3, 'Algerian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (4, 'Armenian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (5, 'Austrian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (6, 'Australian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (7, 'Belgian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (8, 'British', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (9, 'Bulgarian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (10, 'Chilean', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (11, 'Chinese', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (12, 'Cuban', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (13, 'Czech', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (14, 'Danish', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (15, 'Dutch', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (16, 'Ecuadorian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (17, 'Finnish', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (18, 'French', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (19, 'German', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (20, 'Greek', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (21, 'Hungarian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (22, 'Indian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (23, 'Indonesian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (24, 'Irish', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (25, 'Italian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (26, 'Jamaican', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (27, 'Korean', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (28, 'Latin American', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (29, 'Lebanese', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (30, 'Libyan', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (31, 'Luxembourg', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (32, 'Mauritian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (33, 'Mexican', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (34, 'Middle Eastern', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (35, 'Moroccan', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (36, 'North African', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (37, 'North American', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (38, 'Norwegian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (39, 'Oriental', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (40, 'Paraguayan', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (41, 'Peruvian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (42, 'Polish', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (43, 'Portuguese', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (44, 'Romanian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (45, 'Russian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (46, 'Serbian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (47, 'Spanish', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (48, 'Swedish', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (49, 'Swiss', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (50, 'Syrian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (51, 'Tex-Mex', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (52, 'Thai', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (53, 'Tunisian', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (54, 'Turkish', '', 1);
INSERT INTO `category` (`id`, `name`, `description`, `category_type_id`) VALUES (55, 'Vietnamese', '', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `cookbook`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `cookbook` (`id`, `user_id`, `title`, `author`, `description`, `image_url`) VALUES (1, 1, 'On Cooking: A Textbook of Culinary Fundamentals, 6th edition', 'Sarah R. Labensky', 'For over two decades, On Cooking: A Textbook of Culinary Fundamentals has prepared students for successful careers in the culinary arts. Clear and comprehensive, this best-selling text teaches the “hows” and “whys” of cooking and baking principles, while providing step-by-step instructions, visual guidance, and recipes to clarify techniques. The 6th edition expands its “fundamentals” approach, reflects key trends, and adds information on healthy cooking, sous-vide, curing, and smoking, plus dozens of new recipes and more than 200 new photographs.', 'https://www.pearson.com/store');
INSERT INTO `cookbook` (`id`, `user_id`, `title`, `author`, `description`, `image_url`) VALUES (2, 1, 'The 4-Hour Chef', 'Tim Ferriss', 'The 4-Hour Chef: The Simple Path to Cooking Like a Pro, Learning Anything, and Living the Good Life is the third book by Tim Ferriss, published on November 20, 2012.[1][2] Like Ferriss\' other \"4-Hour\" books, The 4-Hour Chef revolves around a theme of self-improvement; this time, through the lens of cooking', 'https://en.wikipedia.org/wiki/The_4-Hour_Chef#/media/File:The_4-Hour_Chef.jpg');
INSERT INTO `cookbook` (`id`, `user_id`, `title`, `author`, `description`, `image_url`) VALUES (3, 2, 'Laurel\'s Kitchen', 'Laurel Robertson', 'Laurel\'s Kitchen is a vegetarian cookbook, first published in 1976, that contributed to the increasing awareness of vegetarian eating in the US.[1] Its authors were Laurel Robertson, Carol Flinders, and Bronwen Godfrey, and its subtitle was a handbook for vegetarian cookery & nutrition. A second edition,', 'https://upload.wikimedia.org/wikipedia/en/b/bc/Laurel%27s_Kitchen.jpg');
INSERT INTO `cookbook` (`id`, `user_id`, `title`, `author`, `description`, `image_url`) VALUES (4, 1, 'Moosewood Cookbook', 'Mollie Katzen', 'The Moosewood Cookbook is a vegetarian cookbook written by Mollie Katzen when she was a member of the Moosewood collective in Ithaca, New York. The original First Edition, self-published in 1974 by Moosewood, was a spiral bound paper-covered book, with photographs of the restaurant staff, with illustrations hand-drawn and text hand-written by Molly Katzen. It was printed by the Glad Day Press in Ithaca. ', 'https://upload.wikimedia.org/wikipedia/en/3/3b/Moosewood_Cookbook_1e_cover.jpg');
INSERT INTO `cookbook` (`id`, `user_id`, `title`, `author`, `description`, `image_url`) VALUES (5, 2, 'The Modern Cook', 'Charles Elmé Francatelli', 'The Modern Cook was the first cookery book by the Anglo-Italian cook Charles Elmé Francatelli (1805–1876). It was first published in 1846. It was popular for half a century in the Victorian era, running through 29 London editions by 1896. It was also published in America.', 'https://upload.wikimedia.org/wikipedia/commons/6/62/Francatelli_modern_cook_1872_21st_edition_title_page.jpg');
INSERT INTO `cookbook` (`id`, `user_id`, `title`, `author`, `description`, `image_url`) VALUES (6, 1, 'White Heat\n', 'Marco Pierre White', 'White Heat is a cookbook by chef Marco Pierre White, published in 1990. It features black-and-white photographs by Bob Carlos Clarke. It is partially autobiographical, and is considered to be the chef\'s first memoir. The book is cited today as having influenced the careers of several Michelin starred and celebrity chefs, and was described by one critic as \"possibly the most influential recipe book of the last 20 years\"', 'https://upload.wikimedia.org/wikipedia/en/1/1e/White_heat_first_edition_cover.jpg');
INSERT INTO `cookbook` (`id`, `user_id`, `title`, `author`, `description`, `image_url`) VALUES (7, 2, 'Hellbent for Cooking', 'Annick Giroux', 'Hellbent for Cooking: The Heavy Metal Cookbook (978-09796163-7-2) is a cookbook by Annick Giroux. It contains 101 recipes by metal bands from 32 countries. The book was published in December 2009 by Bazillion Points.', 'https://upload.wikimedia.org/wikipedia/en/3/35/Hellbent_for_Cooking.jpg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `recipe_has_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `recipe_has_category` (`recipe_id`, `category_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `cookbook_has_recipe`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `cookbook_has_recipe` (`cookbook_id`, `recipe_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `recipe_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `recipe_rating` (`user_id`, `recipe_id`, `rating`, `created_on`) VALUES (1, 1, 5, '2022-01-01 00:00:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `recipe_review`
-- -----------------------------------------------------
START TRANSACTION;
USE `recipe`;
INSERT INTO `recipe_review` (`user_id`, `recipe_id`, `created_on`, `comment`, `active`) VALUES (1, 1, '2022-01-01 00:00:00', 'I absolutely love this, so yummmy!!!', 1);

COMMIT;

