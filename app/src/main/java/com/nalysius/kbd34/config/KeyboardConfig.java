package com.nalysius.kbd34.config;

/**
 * KeyboardConfig is a wrapper around Keyboard / KeyboardView.
 *
 * Instead of setting the keyboard layout and behaviour in the XML,
 * a KeyboardConfig will be used to define keyboards.
 * It should allow the user to use this application as a keyboard creator,
 * not only a keyboard.
 */
public class KeyboardConfig {

    /**
     * KeyConfig represent the configuration for a Key.
     *
     * TODO: add a way to map an action instead of a char to a key.
     *       An action could be "move backward", or "show the
     *       emoji panel".
     */
    class KeyConfig {
        /**
         * The name of the key.
         *
         * The name is not displayed on the key, but is used to
         * reference the key.
         */
        private String name;
        /**
         * The label that is printed on the key.
         */
        private String label;

        /**
         * The characters that can be printed when the key is pressed.
         *
         * If several characters are contained in this array, only one
         * character will be printed. Depending on the keyboard configuration,
         * the printed character could be determined by the number of tap on
         * the key (as in traditional 12 keys keyboards) or by keeping the key
         * pressed and choosing the character in a list.
         *
         * The values are Unicode 16-bits.
         */
        private char[] chars;

        /**
         * The char that can be printed when the key is long pressed.
         */
        private char longPressChar;

        public KeyConfig(String label, char[] chars, char longPressChar)
        {
            this.label = label;
            this.chars = chars;
            this.longPressChar = longPressChar;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public char[] getChars() {
            return chars;
        }

        public void setChars(char[] chars) {
            this.chars = chars;
        }

        public char getLongPressChar() {
            return longPressChar;
        }

        public void setLongPressChar(char longPressChar) {
            this.longPressChar = longPressChar;
        }
    }

    /**
     * The name of the keyboard.
     *
     * Each keyboard MUST have a unique name.
     */
    private String name;

    /**
     * Whether the keyboard is editable.
     *
     * Manually defined keyboard configs aren't editable, they would
     * be cloned with a different name and then edited. This way,
     * a user could edit any keyboards, without loosing the original
     * configuration.
     */
    private boolean editable;

    /**
     * The keys contained in the keyboard.
     */
    private KeyConfig[] keys;
}
