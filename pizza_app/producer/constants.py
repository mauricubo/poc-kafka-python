from models import PizzaEntity, PizzaToppingEntity

PIZZAS = [
    PizzaEntity(name="Margherita", price=4),
    PizzaEntity(name="Marinara", price=4),
    PizzaEntity(name="Diavola", price=7),
    PizzaEntity(name="Mari & Monti", price=8),
    PizzaEntity(name="Salami", price=6),
    PizzaEntity(name="Peperoni", price=6)
]

TOPPINGS = [
    PizzaToppingEntity(name="🍅 tomato", price=1),
    PizzaToppingEntity(name="🧀 blue cheese", price=2),
    PizzaToppingEntity(name="🥚 egg", price=1),
    PizzaToppingEntity(name="🫑 green peppers", price=1),
    PizzaToppingEntity(name="🌶️ hot pepper", price=1),
    PizzaToppingEntity(name="🥓 bacon", price=2),
    PizzaToppingEntity(name="🫒 olives", price=2),
    PizzaToppingEntity(name="🧄 garlic", price=1),
    PizzaToppingEntity(name="🐟 tuna", price=1),
    PizzaToppingEntity(name="🧅 onion", price=1),
    PizzaToppingEntity(name="🍍 pineapple", price=1),
    PizzaToppingEntity(name="🍓 strawberry", price=1),
    PizzaToppingEntity(name="🍌 banana", price=1)
]

PIZZA_SHOPS = [
    "Marios Pizza",
    "Mauricio Pizza",
    "Luigis Pizza",
    "Circular Pi Pizzeria",
    "Ill Make You a Pizza You Can" "t Refuse",
    "Mammamia Pizza",
    "Its-a me! Mario Pizza!",
]