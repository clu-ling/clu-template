package {{ cookiecutter.class }}.mr.{{ cookiecutter.project_name }}.events

import {{ cookiecutter.class }}.mr.TestUtils._
import org.scalatest.{ FlatSpec, Matchers }


class TestEvents extends FlatSpec with Matchers {

    // EXAMPLE Tests

    // "MachineReadingSystem" should "find Query events" in {

    //   val testCases = Seq(
    //     ExistsMentionTestCase( 
    //       labels = Seq(
    //         PositiveLabelTestCase("Query"), 
    //         PositiveLabelTestCase("WhatQuery")
    //       ),
    //       mentionSpan = PositiveTextTestCase("Find ports near Hamburg with enough excess cargo capacity to handle shipments redirected from Hamburg before last week"),
    //       text = "Find ports near Hamburg with enough excess cargo capacity to handle shipments redirected from Hamburg before last week",
    //       args = List(
    //         PositiveArgTestCase(
    //           role = PositiveRoleTestCase("need"),
    //           labels = Seq(
    //             PositiveLabelTestCase("UnspecifiedPort"),
    //             PositiveLabelTestCase("Location"),
    //           ),
    //           text = PositiveTextTestCase("ports")
    //         ),
    //         PositiveArgTestCase( 
    //           role = PositiveRoleTestCase("constraints"),
    //           labels = Seq(PositiveLabelTestCase("ProximityConstraint"), PositiveLabelTestCase("Constraint")),
    //           text = PositiveTextTestCase("near Hamburg")
    //         ),
    //         PositiveArgTestCase( 
    //           role = PositiveRoleTestCase("constraints"),
    //           labels = Seq(PositiveLabelTestCase("QuantityConstraint"), PositiveLabelTestCase("Constraint")),
    //           text = PositiveTextTestCase("enough excess cargo capacity")
    //         ),
    //         PositiveArgTestCase( 
    //           role = PositiveRoleTestCase("constraints"),
    //           labels = Seq(PositiveLabelTestCase("TimeConstraint"), PositiveLabelTestCase("BeforeTime")),
    //           text = PositiveTextTestCase("before last week")
    //         )
    //       )
    //     )
    //   )
    //   testCases foreach { tc =>
    //     val results = system.extract(tc.text)
    //     results should not be empty
    //     checkMention(tc, results) should be (true)
    //   }
    // }

    // it should "not find Query events" in {

    //   val testCases = Seq(
    //     ForAllMentionTestCase(
    //       labels = Seq(NegativeLabelTestCase("CargoQuery"), NegativeLabelTestCase("QuantityQuery")),
    //       text = "Some zebras are galloping to Scotland from Zimbabwe",
    //       mentionSpan = NegativeTextTestCase("Some zebras are heading to Scotland from Zimbabwe"),
    //       args = List( 
    //          NegativeArgTestCase(
    //           role = PositiveRoleTestCase("need"),
    //           labels = Seq(PositiveLabelTestCase("QuantifiedCargo")),
    //           text = PositiveTextTestCase("zebras")
    //         ),
    //         NegativeArgTestCase(
    //           role = PositiveRoleTestCase("constraints"),
    //           labels = Seq(PositiveLabelTestCase("OriginConstraint"), PositiveLabelTestCase("Constraint")),
    //           text = PositiveTextTestCase("Zimbabwe")
    //         ),
    //         NegativeArgTestCase(
    //           role = PositiveRoleTestCase("constraints"),
    //           labels = Seq(PositiveLabelTestCase("DestinationConstraint"), PositiveLabelTestCase("Constraint")),
    //           text = PositiveTextTestCase("Scotland")
    //         )
    //       )
    //     ),
    //     ForAllMentionTestCase(
    //       labels = Seq(NegativeLabelTestCase("CargoQuery"), NegativeLabelTestCase("QuantityQuery")),
    //       text = "Many zebras are galloping to Scotland from Zimbabwe?",
    //       mentionSpan = NegativeTextTestCase("tomfoolery") // wrong mentionSpan
    //     ),
    //   )
    //   testCases foreach { tc =>
    //     val results = system.extract(tc.text)
    //     results should not be empty
    //     checkMention(tc, results) should be (true)
    //   }
    // }

}