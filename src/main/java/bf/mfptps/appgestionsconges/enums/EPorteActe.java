package bf.mfptps.appgestionsconges.enums;

public enum EPorteActe {
	INDIVIDUEL("INDIVIDUEL", "INDIVIDUEL"),
    GROUPE("GROUPE", "GROUPE");

    protected String value;
    protected String label;

    EPorteActe(String value,
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

    public static EPorteActe getByValue(String value)
    {
        for (EPorteActe val : EPorteActe.values())
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

        String[] labels = new String[EPorteActe.values().length];
        int      index  = 0;
        for (EPorteActe val : EPorteActe.values())
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

    public static EPorteActe getByLabel(String label)
    {
        for (EPorteActe val : EPorteActe.values())
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
        EPorteActe eTranslationKind = getByLabel(label);
        if (null != eTranslationKind)
        {
            return getByLabel(label).getValue();
        }
        return null;
    }
    
}
