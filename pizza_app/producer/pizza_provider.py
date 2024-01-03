import random
from faker.providers import BaseProvider

class PizzaProvider(BaseProvider):
    def pizza_name(self):
        validPizzaNames = [
            'Margherita',
            'Marinara',
            'Diavola',
            'Mari & Monti',
            'Salami',
            'Peperoni'
        ]
        return random.choice(validPizzaNames)

    def pizza_topping(self):
        available_pizza_toppings = [
             "🍅 tomato",
            "🧀 blue cheese",
            "🥚 egg",
            "🫑 green peppers",
            "🌶️ hot pepper",
            "🥓 bacon",
            "🫒 olives",
            "🧄 garlic",
            "🐟 tuna",
            "🧅 onion",
            "🍍 pineapple",
            "🍓 strawberry",
            "🍌 banana"
        ]
        return random.choice(available_pizza_toppings)

    def pizza_shop(self):
        pizza_shops = [
            "Marios Pizza",
            "Mauricio Pizza",
            "Luigis Pizza",
            "Circular Pi Pizzeria",
            "Ill Make You a Pizza You Can" "t Refuse",
            "Mammamia Pizza",
            "Its-a me! Mario Pizza!",
        ]
        return random.choice(pizza_shops)