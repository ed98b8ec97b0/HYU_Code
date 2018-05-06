public class Table extends JLabel {
    public Table(int index) {
        setText(String.valueOf(index));
        setBorder(new LineBorder(Color.BLACK, 1));
        setBackground(Color.WHITE);
        setOpaque(true);
        setHorizontalAlignment(JLabel.CENTER);
    }
}
