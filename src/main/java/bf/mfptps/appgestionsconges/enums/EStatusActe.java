package bf.mfptps.appgestionsconges.enums;

public enum EStatusActe {
	INITIATION("INITIATION", "INITIATION"),
    VALIDE("VALIDE", "VALIDE");

    protected String value;
    protected String label;

    EStatusActe(String value,
                  String pLabel)
    {
        this.value = value;
        this.label = pLabel;
    }

    public String getLabel()
    {
        return this.label;
    }

    public String getValue()
    {
        return this.value;
    }

    public static EStatusActe getByValue(String value)
    {
        for (EStatusActe val : EStatusActe.values())
        {
            if (val.getValue().equals(value))
            {
                return val;
            }
        }
        return null; //or throw new IllegalArgumentException("...");
    }

    public static String[] getLabels()
    {

        String[] labels = new String[EStatusActe.values().length];
        int      index  = 0;
        for (EStatusActe val : EStatusActe.values())
        {
            labels[index] = val.getLabel();
            index++ ;
        }
        return labels;
    }

    public static String getLabelByValue(String value)
    {
        return getByValue(value).getLabel();
    }

    public static EStatusActe getByLabel(String label)
    {
        for (EStatusActe val : EStatusActe.values())
        {
            if (val.getLabel().equals(label))
            {
                return val;
            }
        }
        return null; // ou throw new IllegalArgumentException("...");
    }

    public static String getValueByLabel(String label)
    {
        EStatusActe eTranslationKind = getByLabel(label);
        if (null != eTranslationKind)
        {
            return getByLabel(label).getValue();
        }
        return null;
    }
    
}
