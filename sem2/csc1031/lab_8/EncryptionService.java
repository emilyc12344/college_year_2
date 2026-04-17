import java.util.Base64;

class EncryptionService {
    private EncryptionStrategy strategy;

    public void setEncryptionStrategy(EncryptionStrategy strategy) {
        this.strategy = strategy;
    }
    public String encrypt(String text) {
        return this.strategy.encrypt(text);
    }
}

interface EncryptionStrategy {
    String encrypt(String text);
}

class CaesarCipherEncryption implements EncryptionStrategy {
    private int shift;

    public CaesarCipherEncryption(int shift) {
        this.shift = shift;
    }

    public String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                ch = (char) ((ch - base + this.shift) % 26 + base);
            }
            result.append(ch);
        }
        return result.toString();
    }
}

class Base64Encryption implements EncryptionStrategy {
    public String encrypt(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }
}

class XOREncryption implements EncryptionStrategy {
    private int key;

    public XOREncryption(int key) {
        this.key = key;
    }

    public String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            result.append((char) (ch ^ this.key));
        }
        return result.toString();
    }
}

class ReverseStringEncryption implements EncryptionStrategy {
    public String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            result.insert(0, ch);
        }
        return result.toString();
    }
}

class DuplicateCharacterEncryption implements EncryptionStrategy {
    public String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            result.append(ch);
            result.append(ch);
        }
        return result.toString();
    }
}