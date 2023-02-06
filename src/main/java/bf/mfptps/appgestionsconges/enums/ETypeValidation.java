package bf.mfptps.appgestionsconges.enums;

public enum ETypeValidation {
	APPROVED("APPROVED", "APPROUVEE"),
    REJECTED("REJECTED", "REJETEE");

    protected String value;
    protected String label;

    ETypeValidation(String value,
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

    public static ETypeValidation getByValue(String value)
    {
        for (ETypeValidation val : ETypeValidation.values())
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

        String[] labels = new String[ETypeValidation.values().length];
        int      index  = 0;
        for (ETypeValidation val : ETypeValidation.values())
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

    public static ETypeValidation getByLabel(String label)
    {
        for (ETypeValidation val : ETypeValidation.values())
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
        ETypeValidation eTranslationKind = getByLabel(label);
        if (null != eTranslationKind)
        {
            return getByLabel(label).getValue();
        }
        return null;
    }
    
}
