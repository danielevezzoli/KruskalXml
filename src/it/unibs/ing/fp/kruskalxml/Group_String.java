package it.unibs.ing.fp.kruskalxml;

public class Group_String {
	
	
	// StringBuffer che conterrà i set del gruppo corrispondente
	StringBuffer set=new StringBuffer();
	
	
	/*
	 * Aggiunge un set (un carattere solo o anche più caratteri)
	 * @author Stefano Poma
	 * @param stringa che contine i set (i label dei singoli nodi)
	 * 
	 */
	public void addSet (String str)
	{
		set.append(str);
	}
	
	/*
	 * Ritorna il set
	 * @author
	 * @return stringa che contiene il set
	 */
	public String getSet()
	{
		return set.toString();
	}
	
	/*
	 * controlla se nello StringBuffer è presente uno specifico set (uno o più caratteri)
	 * @author
	 * @param una CharSequence che contiene il set da confrontare
	 * @return vero se lo contiene, falso altrimenti
	 */
	public boolean contain1Set (CharSequence a)
	{
		boolean flag=false;
		String ritorno=set.toString();
		if (ritorno.contains(a))
			flag=true;
			
			return flag;
	}
	
	/*
	 * controlla se 2 set sono contenuti entrambi nello StringBuffer
	 * @author
	 * @param 2 CharSequence
	 * @return vero se li contiene, falso altrimenti
	 */
	public boolean contain2Set (CharSequence a, CharSequence b)
	{
		boolean flag=false;
		
		if (contain1Set(a) && contain1Set(b))
			flag=true;
			
			return flag;
	}

}
