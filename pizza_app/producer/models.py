
class PizzaEntity:
    def __init__(self, name, price):
        self.name = name
        self.price = price

class PizzaToppingEntity:
    def __init__(self, name, price):
        self.name = name
        self.price = price
        
class PizzaToppingAggregate:
    def __init__(self, pizza: PizzaEntity, additionalToppings = []):
        self.pizza = pizza
        self.additionalToppings = additionalToppings
    
    def add_topping(self, topping: PizzaToppingEntity):
        self.additionalToppings.append(topping)
    
    def get_price(self):
        price = self.pizza.price
        for topping in self.additionalToppings:
            price += topping.price
        return price

    def convert_to_message(self):
        toppingNames = []
        for topping in self.additionalToppings:
            toppingNames.append(topping.name)
            
        return {
            "pizzaName": self.pizza.name,
            "additionalToppings": toppingNames,
            "price": self.get_price()
        }