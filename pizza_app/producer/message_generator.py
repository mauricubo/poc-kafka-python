from faker import Faker
from pizza_provider import PizzaProvider
import random
import time

class MessageGenerator():

    def produce_msg(
            self,
            order_count=1,
            max_pizzas_in_order=5,
            max_toppings_in_pizza=3
        ):
        fake = Faker("DE")
        fake.add_provider(PizzaProvider)
        shop = fake.pizza_shop()
        pizzas = []
        # add random amount of pizzas to the order
        for pizza in range(random.randint(1, max_pizzas_in_order)):
            toppings = []
            # for every pizza, add random amount of toppings
            for topping in range(random.randint(0, max_toppings_in_pizza)):
                toppings.append(fake.pizza_topping())
            pizzas.append(
                {
                    "pizzaName": fake.pizza_name(),
                    "additionalToppings": toppings
                }
            )
        # message composition
        message = {
            'id': order_count,
            'shop': shop,
            'name': fake.unique.name(),
            'phoneNumber': fake.unique.phone_number(),
            'address': fake.address(),
            'pizzas': pizzas,
            'timestamp': int(time.time() * 1000)
        }
        key = {'shop': shop}
        return message, key