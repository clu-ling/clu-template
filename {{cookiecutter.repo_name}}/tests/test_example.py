import {{cookiecutter.namespace}}.{{cookiecutter.package_name}}
from {{cookiecutter.namespace}}.{{cookiecutter.package_name}} import info
from .utils import EXAMPLE
import unittest

# see https://docs.python.org/3/library/unittest.html#basic-example
class TestTemplate(unittest.TestCase):

    def test_example(self):
        self.assertEquals(EXAMPLE, "Example")

    def test_package(self):
        {% set authors_list = cookiecutter.authors.split(',') %}
        self.assertEquals(info.authors, {{ authors_list }})
