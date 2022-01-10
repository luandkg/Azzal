package Tronarko;

import java.awt.Color;
import java.util.ArrayList;

import Tronarko.Tronarko.Tozte;

public class TozteCor {

	private String mNome;
	private Tozte mTozte;
	private Color mCor;

	private ArrayList<Tozte> mToztes;

	public TozteCor(String eNome, Tozte eTozte, Color eCor) {
		mNome = eNome;
		mTozte = eTozte;
		mCor = eCor;
		
		mToztes = new ArrayList<Tozte>();
		
	}

	public String getNome() {
		return mNome;
	}

	public void setNome(String eNome) {
		mNome = eNome;
	}

	public Tozte getTozte() {
		return mTozte;
	}

	public Color getCor() {
		return mCor;
	}
	
	public int getOrdem() {
		return mTozte.getSuperarko() + ((mTozte.getHiperarko() - 1) * 50) + (mTozte.getTronarko() * 500);
	}

	
	public ArrayList<Tozte> getToztes() {
		return mToztes;
	}
	
	
	public void adicionar_Tozte(Tozte eTozte) {
		mToztes.add(eTozte);
	}
	
	
	public Tozte getTozteMin() {
		
		Tozte ret = null;
		
		if (mToztes.size()>0) {
			
			ret = mToztes.get(0);
			
			for(Tozte eTozte : mToztes) {
				
				if (eTozte.MenorrQue(ret)) {
					ret=eTozte;
				}
				
			}
			
		}
		
				
		return ret;
		
	}
	
	
	public Tozte getTozteMax() {
		
		Tozte ret = null;
		
		if (mToztes.size()>0) {
			
			ret = mToztes.get(0);
			
			for(Tozte eTozte : mToztes) {
				
				if (eTozte.MaiorQue(ret)) {
					ret=eTozte;
				}
				
			}
			
		}
		
				
		return ret;
		
	}
	
	
	public String getComplemento() {
		
		String ret = "";
		
		if (mToztes.size()==0) {
			
			
			
		}else {
			
			if (mToztes.size()==1) {
				
				ret =  "[ " + getTozteMin().toString() + " ]";
				
			}else {
				
				
				ret =  "[ " + getTozteMin().toString() + " a " + getTozteMax().toString() + " ]";
				
			}
			
		}
		
		
		return ret;
		
		
	}
	
	
}
