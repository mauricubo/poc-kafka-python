from faker import Faker
from models import PizzaToppingAggregate
from constants import PIZZAS, TOPPINGS, PIZZA_SHOPS
import random
import time

class MessageGenerator():

    def __random_select(self, array):
        return array[random.randint(0, len(array) - 1)]

    def produce_msg(
            self,
            order_count=1,
            max_pizzas_in_order=5,
            max_toppings_in_pizza=3
        ):
        fake = Faker("DE")
        shop = self.__random_select(PIZZA_SHOPS)
        pizzas = []
        # add random amount of pizzas to the order
        for pizza_index in range(random.randint(1, max_pizzas_in_order)):
            pizza = self.__random_select(PIZZAS)
            pizzaToppingAggregate = PizzaToppingAggregate(
                pizza=pizza,
                additionalToppings=[]
            )
            # for every pizza, add random amount of toppings
            for toppic_index in range(random.randint(0, max_toppings_in_pizza)):
                topping = self.__random_select(TOPPINGS)
                pizzaToppingAggregate.add_topping(topping)
            converted = pizzaToppingAggregate.convert_to_message()
            pizzas.append(converted)
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