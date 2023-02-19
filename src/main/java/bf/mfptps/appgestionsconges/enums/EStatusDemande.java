package bf.mfptps.appgestionsconges.enums;

public enum EStatusDemande {
	INITIATION("INITIATION", "INITIATION"),
	VALIDE("VALIDE", "VALIDE"),
	REJETE("REJETE", "REJETE");

    protected String value;
    protected String label;

    EStatusDemande(String value,
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

    public static EStatusDemande getByValue(String value)
    {
        for (EStatusDemande val : EStatusDemande.values())
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

        String[] labels = new String[EStatusDemande.values().length];
        int      index  = 0;
        for (EStatusDemande val : EStatusDemande.values())
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

    public static EStatusDemande getByLabel(String label)
    {
        for (EStatusDemande val : EStatusDemande.values())
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
        EStatusDemande eTranslationKind = getByLabel(label);
        if (null != eTranslationKind)
        {
            return getByLabel(label).getValue();
        }
        return null;
    }
    
}
