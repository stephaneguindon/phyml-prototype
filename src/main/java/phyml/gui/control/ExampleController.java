package phyml.gui.control;

import com.google.common.collect.Lists;
import phyml.gui.model.AbstractProperty;
import phyml.gui.model.Node;
import phyml.gui.view.ComboBoxProperty;
import phyml.gui.view.RadioButtonProperty;
import phyml.gui.view.TextFieldProperty;

import java.beans.PropertyChangeEvent;
import java.util.List;

/**
 * Example controller to demonstrate how to create and control a gui.
 *
 * <p/>
 * Written by: Markus Binsteiner
 * Date: 10/10/13
 * Time: 10:51 AM
 */
public class ExampleController extends NodeController {

    public static void main(String[] args) {

        // create a controller
        ExampleController exampleController = new ExampleController();

        // create the GUI
        FormCreator fc = new FormCreator(exampleController);

        // alternatively, you can use different layouts
        //FormCreator fc = new FormCreator(exampleController, FormCreator.SIMPLE_LAYOUT);
        //FormCreator fc = new FormCreator(exampleController, FormCreator.TABBED_LAYOUT);

        // display the gui
        fc.display();

    }



    public static final String NODE_1 = "node1";
    public static final String NODE_2 = "node2";
    public static final String NODE_3 = "node3";

    public static final String PROPERTY_1 = "label1";
    public static final String PROPERTY_2 = "label2";
    public static final String PROPERTY_3 = "label3";
    public static final String PROPERTY_4 = "label4";


    @Override
    protected List<Node> initNodes() {

        // create node1
        Node node1 = new Node(NODE_1);
        // adding property for node1 and setting default value
        AbstractProperty prop1 = new TextFieldProperty(node1, PROPERTY_1);
        prop1.selectValue("exampleDefaultValue");

        //creating node2
        Node node2 = new Node(NODE_2);
        // creating 2 properties and setting default value for node 2
        AbstractProperty prop21 = new RadioButtonProperty(node2, PROPERTY_2);
        prop21.setOption(RadioButtonProperty.OPTION_1, "button1");
        prop21.setOption(RadioButtonProperty.OPTION_2, "button2");
        prop21.selectValue("button2");
        AbstractProperty prop22 = new RadioButtonProperty(node2, PROPERTY_3);
        prop22.setOption(RadioButtonProperty.OPTION_1, "button3");
        prop22.setOption(RadioButtonProperty.OPTION_2, "button4");
        prop22.selectValue("button3");

        // creating node 3
        Node node3 = new Node(NODE_3);
        // creating property for node 3
        String choices = "Dayhoff;LG;WAG;JTT";
        AbstractProperty prop3 = new ComboBoxProperty(node3, PROPERTY_4);
        prop3.setOption(ComboBoxProperty.OPTION_CHOICES, choices);


        // adding all nodes to list for creation
        List<Node> nodes = Lists.newArrayList();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);

        return nodes;
    }

    @Override
    public void nodeChanged(Node node, AbstractProperty property, PropertyChangeEvent event) {

        String labelThatChanged = property.getLabel();

        // disable property 1 if 'JTT' is selected in property 4
        if (PROPERTY_4.equals(labelThatChanged)) {

            AbstractProperty prop = getProperty(PROPERTY_1);

            if ("JTT".equals(event.getNewValue())) {
                prop.setActive(false);
            } else {
                prop.setActive(true);
            }
        } else {
            myLogger.debug("Doing nothing.");
        }


    }
}