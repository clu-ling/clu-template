package {{ cookiecutter.class_path }}.mr.{{ cookiecutter.project_name }}.entities

//import TestUtils._
import {{ cookiecutter.class_path }}.mr.TestUtils._
import org.scalatest.{ FlatSpec, Matchers }


class TestEntities extends FlatSpec with Matchers {
    
  "{{ cookiecutter.project_name }} MachineReadingSystem" should "identify TimeExpression mentions" in {

    val testCases = Seq(
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression")
        ),
        mentionSpan = PositiveTextTestCase("August 24th 2020"),
        text = "August 24th 2020"
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression"),
          PositiveLabelTestCase("OnTime")
        ),
        mentionSpan = PositiveTextTestCase("on August 24th 2020"),
        text = "on August 24th 2020" 
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression"),
          PositiveLabelTestCase("AfterTime")
        ),
        mentionSpan = PositiveTextTestCase("after August 24, 2020"),
        text = "after August 24, 2020" 
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression"), 
          PositiveLabelTestCase("BeforeTime")
        ),
        mentionSpan = PositiveTextTestCase("by August 24th 2020"),
        text = "by August 24th 2020" 
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression"), 
          PositiveLabelTestCase("IntervalTime")
        ),
        mentionSpan = PositiveTextTestCase("from August 12, 2020 to August 19, 2020"),
        text = "from August 12, 2020 to August 19, 2020" 
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression"), 
          PositiveLabelTestCase("IntervalTime")
        ),
        mentionSpan = PositiveTextTestCase("during the week"),
        text = "during the week",
        foundBy = Some("interval-time-expression")
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression"), 
          PositiveLabelTestCase("IntervalTime")
        ),
        mentionSpan = PositiveTextTestCase("throughout 1991"),
        text = "throughout 1991"
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression"), 
          PositiveLabelTestCase("TimeUnit")
        ),
        mentionSpan = PositiveTextTestCase("the next few days"),
        text = "the next few days"
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression"), 
          PositiveLabelTestCase("Date")
        ),
        mentionSpan = PositiveTextTestCase("August 2020"),
        text = "August 2020" 
      ),
      ExistsMentionTestCase(
        labels = Seq(PositiveLabelTestCase("TimeExpression"), 
        PositiveLabelTestCase("Date")
        ),
        mentionSpan = PositiveTextTestCase("August 12 2020"),
        text = "August 12 2020" 
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression"), 
          PositiveLabelTestCase("Date")
        ),
        mentionSpan = PositiveTextTestCase("12/02/1986"),
        text = "12/02/1986" 
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression"), 
          PositiveLabelTestCase("Date")
        ),
        mentionSpan = PositiveTextTestCase("05/1986"),
        text = "05/1986" 
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression"), 
          PositiveLabelTestCase("Date")
        ),
        mentionSpan = PositiveTextTestCase("05/86"),
        text = "05/86" 
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression"), 
          PositiveLabelTestCase("Date")
        ),
        mentionSpan = PositiveTextTestCase("1986-12-21"),
        text = "1986-12-21" 
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression"), 
          PositiveLabelTestCase("Date")
        ),
        mentionSpan = PositiveTextTestCase("2012 11 30"),
        text = "2012 11 30" 
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression"), 
          PositiveLabelTestCase("Date")
        ),
        mentionSpan = PositiveTextTestCase("12 JUN 2021"),
        text = "12 JUN 2021" 
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression"), 
          PositiveLabelTestCase("Date")
        ),
        mentionSpan = PositiveTextTestCase("12 Jun 2021"),
        text = "12 Jun 2021" 
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression"), 
          PositiveLabelTestCase("Date")
        ),
        mentionSpan = PositiveTextTestCase("12-Jun-2021"),
        text = "12-Jun-2021" 
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression"), 
          PositiveLabelTestCase("IntervalTime")
        ),
        mentionSpan = PositiveTextTestCase("During the week of October 12"),
        text = "During the week of October 12" 
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression"),
          PositiveLabelTestCase("TimeUnit")
        ),
        mentionSpan = PositiveTextTestCase("The week ending October 12th"),
        text = "The week ending October 12th" 
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("TimeExpression"),
          PositiveLabelTestCase("IntervalTime")
        ),
        mentionSpan = PositiveTextTestCase("for the next week"),
        text = "for the next week"
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("ComplexIntervalTime")
        ),
        mentionSpan = PositiveTextTestCase("daily for the next week"),
        text = "daily for the next week",
        foundBy = Some("complex-interval-time-expression")
      ),
      ExistsMentionTestCase(
        labels = Seq(
          PositiveLabelTestCase("FiscalYear")
        ),
        mentionSpan = PositiveTextTestCase("FY2017"),
        text = "FY2017",
        foundBy = Some("fiscal-year")
      ),
      ExistsMentionTestCase(
        labels = Seq(PositiveLabelTestCase("Month")),
        mentionSpan = PositiveTextTestCase("Jul"),
        text = "Jul",
        foundBy = Some("month")
      ),
      ForAllMentionTestCase(
        labels = Seq(NegativeLabelTestCase("Month")),
        mentionSpan = NegativeTextTestCase("Jul"),
        text = "Julia"
      ),
      ExistsMentionTestCase(
        labels = Seq(PositiveLabelTestCase("NumericExpression")),
        mentionSpan = PositiveTextTestCase("202k"),
        text = "202k",
        foundBy = Some("numeric-expression")
      ),
      ForAllMentionTestCase(
        labels = Seq(NegativeLabelTestCase("Quantity")),
        mentionSpan = NegativeTextTestCase("k"),
        text = "truck"
      ),
      ExistsMentionTestCase(
        labels = Seq(PositiveLabelTestCase("IntervalTime")),
        mentionSpan = PositiveTextTestCase("between 2001 and 2007"),
        text = "between 2001 and 2007",
        foundBy = Some("interval-time-expression")
      ),
      ForAllMentionTestCase(
        labels = Seq(NegativeLabelTestCase("IntervalTime")),
        mentionSpan = NegativeTextTestCase("between 1900 and 2000"),
        text = "between 1900 and 2000 tons of shipping"
      ),
      ForAllMentionTestCase(
        labels = Seq(NegativeLabelTestCase("AfterTime")),
        mentionSpan = NegativeTextTestCase("post 2000"),
        text = "you should post 2000 articles by July"
      ),
    )

    testCases foreach { tc =>
      val results = system.extract(tc.text)
      results should not be empty
      checkMention(tc, results) should be (true)
    }  
  }

  it should "identify concept mentions" in {
    val testCases = Seq(
      ExistsMentionTestCase( //multiple subtypes
        labels = Seq(PositiveLabelTestCase("Concept")),
        mentionSpan = PositiveTextTestCase("ancient ostrich feathers"),
        text = "ancient ostrich feathers",
        args = List(
          PositiveArgTestCase(
            role = PositiveRoleTestCase("subtype"),
            labels = Seq(PositiveLabelTestCase("Modifier")),
            text = PositiveTextTestCase("ancient")
          ),
          PositiveArgTestCase(
            role = PositiveRoleTestCase("subtype"),
            labels = Seq(PositiveLabelTestCase("Modifier")),
            text = PositiveTextTestCase("ostrich")
          ),
          PositiveArgTestCase(
            role = PositiveRoleTestCase("core"),
            labels = Seq(PositiveLabelTestCase("Concept")),
            text = PositiveTextTestCase("feathers")
          )
        )
      ),
      ExistsMentionTestCase( //multiple subtypes
        labels = Seq(PositiveLabelTestCase("Concept")),
        mentionSpan = PositiveTextTestCase("intercontinental ballistic missile"),
        text = "intercontinental ballistic missile",
        args = List(
          PositiveArgTestCase(
            role = PositiveRoleTestCase("subtype"),
            labels = Seq(PositiveLabelTestCase("Modifier")),
            text = PositiveTextTestCase("intercontinental")
          ),
          PositiveArgTestCase(
            role = PositiveRoleTestCase("subtype"),
            labels = Seq(PositiveLabelTestCase("Modifier")),
            text = PositiveTextTestCase("ballistic")
          ),
          PositiveArgTestCase(
            role = PositiveRoleTestCase("core"),
            labels = Seq(PositiveLabelTestCase("Concept")),
            text = PositiveTextTestCase("missile")
          )
        )
      ),
      ExistsMentionTestCase( //Compound subtype
        labels = Seq(PositiveLabelTestCase("Concept")),
        mentionSpan = PositiveTextTestCase("Chicago-style pizza"),
        text = "Chicago-style pizza",
        args = List(
          PositiveArgTestCase(
            role = PositiveRoleTestCase("subtype"),
            labels = Seq(PositiveLabelTestCase("Modifier")),
            text = PositiveTextTestCase("Chicago-style")
          ),
          PositiveArgTestCase(
            role = PositiveRoleTestCase("core"),
            labels = Seq(PositiveLabelTestCase("Concept")),
            text = PositiveTextTestCase("pizza")
          )
        )
      ),
      ExistsMentionTestCase( //Multiple compound subtypes
        labels = Seq(PositiveLabelTestCase("Concept")),
        mentionSpan = PositiveTextTestCase("Chicago-style thin-crust pizza"),
        text = "Chicago-style thin-crust pizza",
        args = List(
          PositiveArgTestCase(
            role = PositiveRoleTestCase("subtype"),
            labels = Seq(PositiveLabelTestCase("Modifier")),
            text = PositiveTextTestCase("Chicago-style")
          ),
          PositiveArgTestCase(
            role = PositiveRoleTestCase("subtype"),
            labels = Seq(PositiveLabelTestCase("Modifier")),
            text = PositiveTextTestCase("thin-crust")
          ),
          PositiveArgTestCase(
            role = PositiveRoleTestCase("core"),
            labels = Seq(PositiveLabelTestCase("Concept")),
            text = PositiveTextTestCase("pizza")
          )
        )
      ),

      // EX: They pose a national security threat.
      ExistsMentionTestCase(
        labels = Seq(PositiveLabelTestCase("Concept")),
        mentionSpan = PositiveTextTestCase("national security threat"),
        text = "They pose a national security threat.",
        foundBy = Some("concept-serial-mod"),
        args = List(
          PositiveArgTestCase(
            role = PositiveRoleTestCase("subtype"),
            labels = Seq(PositiveLabelTestCase("Modifier")),
            text = PositiveTextTestCase("national")
          ),
          PositiveArgTestCase(
            role = PositiveRoleTestCase("subtype"),
            labels = Seq(PositiveLabelTestCase("Modifier")),
            text = PositiveTextTestCase("security")
          ),
          PositiveArgTestCase(
            role = PositiveRoleTestCase("core"),
            labels = Seq(PositiveLabelTestCase("Concept")),
            text = PositiveTextTestCase("threat")
          )
        )
      ),

      // EX: They pose a political, economic, and geographical threat.
      ExistsMentionTestCase(
        labels = Seq(PositiveLabelTestCase("Concept")),
        mentionSpan = PositiveTextTestCase("political, economic, and geographical threat"),
        text = "They pose a political, economic, and geographical threat.",
        foundBy = Some("concept-coord-mod"),
        args = List(
          PositiveArgTestCase(
            role = PositiveRoleTestCase("subtype"),
            labels = Seq(PositiveLabelTestCase("Modifier")),
            text = PositiveTextTestCase("political")
          ),
          PositiveArgTestCase(
            role = PositiveRoleTestCase("core"),
            labels = Seq(PositiveLabelTestCase("Concept")),
            text = PositiveTextTestCase("threat")
          )
        )
      ),
      ExistsMentionTestCase(
        labels = Seq(PositiveLabelTestCase("Concept")),
        mentionSpan = PositiveTextTestCase("economic, and geographical threat"),
        text = "They pose a political, economic, and geographical threat.",
        foundBy = Some("concept-coord-mod"),
        args = List(
          PositiveArgTestCase(
            role = PositiveRoleTestCase("subtype"),
            labels = Seq(PositiveLabelTestCase("Modifier")),
            text = PositiveTextTestCase("economic")
          ),
          PositiveArgTestCase(
            role = PositiveRoleTestCase("core"),
            labels = Seq(PositiveLabelTestCase("Concept")),
            text = PositiveTextTestCase("threat")
          )
        )
      ),
      ExistsMentionTestCase(
        labels = Seq(PositiveLabelTestCase("Concept")),
        mentionSpan = PositiveTextTestCase("geographical threat"),
        text = "They pose a political, economic, and geographical threat.",
        foundBy = Some("concept-serial-mod"),
        args = List(
          PositiveArgTestCase(
            role = PositiveRoleTestCase("subtype"),
            labels = Seq(PositiveLabelTestCase("Modifier")),
            text = PositiveTextTestCase("geographical")
          ),
          PositiveArgTestCase(
            role = PositiveRoleTestCase("core"),
            labels = Seq(PositiveLabelTestCase("Concept")),
            text = PositiveTextTestCase("threat")
          )
        )
      ),

      // EX: He poses a political, economic, and threat to national security.
      ExistsMentionTestCase(
        labels = Seq(PositiveLabelTestCase("Concept")),
        mentionSpan = PositiveTextTestCase("political, economic, and threat"),
        text = "He poses a political, economic, and threat to national security.",
        foundBy = Some("concept-coord-mod"),
        args = List(
          PositiveArgTestCase(
            role = PositiveRoleTestCase("subtype"),
            labels = Seq(PositiveLabelTestCase("Modifier")),
            text = PositiveTextTestCase("political")
          ),
          PositiveArgTestCase(
            role = PositiveRoleTestCase("core"),
            labels = Seq(PositiveLabelTestCase("Concept")),
            text = PositiveTextTestCase("threat")
          )
        )
      ),
      ExistsMentionTestCase(
        labels = Seq(PositiveLabelTestCase("Concept")),
        mentionSpan = PositiveTextTestCase("economic, and threat"),
        text = "He poses a political, economic, and threat to national security.",
        foundBy = Some("concept-coord-mod"),
        args = List(
          PositiveArgTestCase(
            role = PositiveRoleTestCase("subtype"),
            labels = Seq(PositiveLabelTestCase("Modifier")),
            text = PositiveTextTestCase("economic")
          ),
          PositiveArgTestCase(
            role = PositiveRoleTestCase("core"),
            labels = Seq(PositiveLabelTestCase("Concept")),
            text = PositiveTextTestCase("threat")
          )
        )
      ),
      ExistsMentionTestCase(
        labels = Seq(PositiveLabelTestCase("Concept")),
        mentionSpan = PositiveTextTestCase("national security"),
        text = "He poses a political, economic, and threat to national security.",
        foundBy = Some("concept-serial-mod"),
        args = List(
          PositiveArgTestCase(
            role = PositiveRoleTestCase("subtype"),
            labels = Seq(PositiveLabelTestCase("Modifier")),
            text = PositiveTextTestCase("national")
          ),
          PositiveArgTestCase(
            role = PositiveRoleTestCase("core"),
            labels = Seq(PositiveLabelTestCase("Concept")),
            text = PositiveTextTestCase("security")
          )
        )
      ),

      // EX: He poses a political, economic, and national security threat.
      ExistsMentionTestCase(
        labels = Seq(PositiveLabelTestCase("Concept")),
        mentionSpan = PositiveTextTestCase("political, economic, and national security threat"),
        text = "He poses a political, economic, and national security threat.",
        foundBy = Some("concept-coord-mod"),
        args = List(
          PositiveArgTestCase(
            role = PositiveRoleTestCase("subtype"),
            labels = Seq(PositiveLabelTestCase("Modifier")),
            text = PositiveTextTestCase("political")
          ),
          PositiveArgTestCase(
            role = PositiveRoleTestCase("core"),
            labels = Seq(PositiveLabelTestCase("Concept")),
            text = PositiveTextTestCase("threat")
          )
        )
      ),
      ExistsMentionTestCase(
        labels = Seq(PositiveLabelTestCase("Concept")),
        mentionSpan = PositiveTextTestCase("economic, and national security threat"),
        text = "He poses a political, economic, and national security threat.",
        foundBy = Some("concept-coord-mod"),
        args = List(
          PositiveArgTestCase(
            role = PositiveRoleTestCase("subtype"),
            labels = Seq(PositiveLabelTestCase("Modifier")),
            text = PositiveTextTestCase("economic")
          ),
          PositiveArgTestCase(
            role = PositiveRoleTestCase("core"),
            labels = Seq(PositiveLabelTestCase("Concept")),
            text = PositiveTextTestCase("threat")
          )
        )
      ),
      ExistsMentionTestCase(
        labels = Seq(PositiveLabelTestCase("Concept")),
        mentionSpan = PositiveTextTestCase("national security threat"),
        text = "He poses a political, economic, and national security threat.",
        foundBy = Some("concept-serial-mod"),
        args = List(
          PositiveArgTestCase(
            role = PositiveRoleTestCase("subtype"),
            labels = Seq(PositiveLabelTestCase("Modifier")),
            text = PositiveTextTestCase("national")
          ),
          PositiveArgTestCase(
            role = PositiveRoleTestCase("subtype"),
            labels = Seq(PositiveLabelTestCase("Modifier")),
            text = PositiveTextTestCase("security")
          ),
          PositiveArgTestCase(
            role = PositiveRoleTestCase("core"),
            labels = Seq(PositiveLabelTestCase("Concept")),
            text = PositiveTextTestCase("threat")
          )
        )
      ),
    )

    testCases foreach { tc =>
      val results = system.extract(tc.text)
      results should not be empty
      checkMention(tc, results) should be (true)
    }
  }
  
}