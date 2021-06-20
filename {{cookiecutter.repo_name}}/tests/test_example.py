import clu.{{cookiecutter.package_name}}
from .utils import *
import unittest

class TestTemplate(unittest.TestCase):

    def test_example(self):
        assert utils.EXAMPLE == "Example"