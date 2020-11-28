package Control;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


final class LayoutListViewCell extends ListCell<String> {
    Pos position1;
    LayoutListViewCell (Pos position){
        this.position1 = position;
    {
    setAlignment(position1);
        Color color = new Color((242.0D/255.0D), (255.0D/255.0D), (231.0D/255.0D), 1D);
        Paint newColor = color;
        CornerRadii radii = null;
        Insets insets = null;
        setBackground(new Background(new BackgroundFill(newColor, radii, insets))); } }


    @Override protected void updateItem(String item, boolean empty)
    { super.updateItem(item, empty); setText(item); } }