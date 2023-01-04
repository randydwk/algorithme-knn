package com.iut.normalization;

import com.iut.model.IColumn;

/**
 * Decrit un <i>Normaliseur</i>, c'est a dire un objet qui peut transformer
 * une valeur d'une colone en une valeur entre 0 et 1.
 *
 * Chaque <i>normaliseur</i> est classe qui implemente l'interface
 * <i>IValueNormalizer</i>.
 *
 * Cette interface inclus un type enumere <i>NormalizerTypes</i> listant
 * tous les normaliseurs connus.
 */
public interface IValueNormalizer {
    /**
     * Liste tous les normaliseurs connus par le programme. A chaque valeur
     * dans cette liste doit correspondre
     * une classe qui implemente <i>INormalizer</i>.
     * <ul>
     * <li>NUMBER_NORMALIZER: Normalise une valeur numerique (entiere ou
     * reelle)</li>
     * <li>BOOLEAN_NORMALIZER: Normalise une valeur booleenne (typiquement
     * <i>false</i>=0 et <i>true</i>=1</li>
     * <li>POKEMON_TYPE_NORMALIZER: Normalise une valeur parmis les types
     * de Pokemon possible. Chaque type recoit une valeur unique entre 0
     * et 1</li>
     * <li>etc. Chaque type enumere cree pour un DataSet doit avoir une
     * constante associee et un <i>normaliseur</i></li>
     * </ul>
     */
    public enum NormalizerTypes {
        NUMBER_NORMALIZER(new NumberNormalizer()),
        BOOLEAN_NORMALIZER(new BooleanNormalizer()),
        ENUMERATIVE_NORMALIZER(new EnumerativeNormalizer()),
        ORDINAL_NORMALIZER(new OrdinalNormalizer()),
        BINARY_NORMALIZER(new BinaryNormalizer());

        IValueNormalizer norm;

        private NormalizerTypes(IValueNormalizer norm) {
            this.norm = norm;
        }

        public IValueNormalizer getNorm() {
            return norm;
        }
    }

    /**
     * Retourne la valeur en parametre normalisee (entre 0 et 1).
     */
    public double normalize(IColumn column, Object value);

    /**
     * De-normalise la valeur en parametre (qui est entre 0 et 1)
     * Retourne la « vraie » valeur correspondante pour la colonne
     * associee au normaliseur
     */
    public Object denormalize(IColumn column, double value);
}