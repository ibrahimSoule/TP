package Tp1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

	public static Noeud racine = null;

	public static void rajoute_fils_droit(Noeud Node, Noeud NodeDroit) {
		Node.setNodeDroit(NodeDroit);

	}

	public static void rajoute_fils_gauche(Noeud Node, Noeud NodeGauche) {
		Node.setNodeDroit(NodeGauche);

	}

	public static Noeud nouveau_noeud(char caractere, int code) {
		Noeud N = new Noeud(caractere, code);

		return N;
	}

	public static Noeud createNoeud(char l, int code) {

		return new Noeud(l, code);

	}

	public static Noeud getracine() {
		return racine;

	}

	public static Noeud getNode(int i) {
		Noeud N = getracine();
		int indice = 0;
		Noeud Node = null;

		while (N != null) {
			if (indice == i) {
				Node = N;
			} else {
				indice++;
				try {
					N = N.getNodeDroit();
				} catch (Exception e) {
					System.out.println("Erreur=" + e);
					N = null;
				}

				System.out.println("INDICE=" + indice + " N=" + N.getCaractere());
			}
		}

		return Node;

	}

	public static void printtree(Noeud n) {
		if (n.getNodeDroit() != null && n.getNodeGauches() == null) {
			System.out.println("  " + n.getNodeDroit().getCaractere() + "(" + n.getNodeDroit().getCode() + ")");
		} else if (n.getNodeDroit() == null && n.getNodeGauches() != null) {
			System.out.println(n.getNodeGauches().getCaractere() + "(" + n.getNodeGauches().getCode() + ")" + "  ");
		} else if (n.getNodeDroit() != null && n.getNodeGauches() != null) {
			System.out.println(n.getNodeGauches().getCaractere() + "(" + n.getNodeGauches().getCode() + ")" + " "
					+ n.getNodeDroit().getCaractere() + "(" + n.getNodeDroit().getCode() + ")");
		}

		else if (n.getNodeDroit() == null && n.getNodeGauches() == null) {
			System.out.println("  ");
		}

		if (n.getNodeDroit() != null) {
			printtree(n.getNodeDroit());
		}
		if (n.getNodeGauches() != null) {
			System.out.println("**" + n.getNodeGauches().getCaractere() + "(" + n.getNodeGauches().getCode() + ")");
			printtree(n.getNodeGauches());
		}

	}

	public static Noeud CreationArbre(String nomfichier) {
		int Numero = 0;
		String mot;
		String line[];
		Noeud NodePrec = null;
		int code = 0;
		try {
			InputStream ips = new FileInputStream(nomfichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;

			String tampon = br.readLine();

			while ((ligne = br.readLine()) != null) {

				line = ligne.split(" ");

				Numero = Integer.parseInt(line[0]);
				mot = line[1];

				NodePrec = null;
				for (int i = 0; i < mot.length(); i++) {

					// condition qui verifier si une racine d'abord

					if (i == (mot.length() - 1)) {
						code = Numero;
					} else {
						code = -1;
					}

					if (i == 0) {
						if (getracine() == null) {
							NodePrec = createNoeud(mot.charAt(i), code);
							racine = NodePrec;
							// System.out.println("Racine=" + mot.charAt(i));
						} else if (getracine() != null) {
							if (getracine().getCaractere() == mot.charAt(i)) {
								NodePrec = getracine();
							} else {

								Noeud tap = getracine();
								boolean b = false;
								// System.out.println("TAB=" +
								// tap.getCaractere());
								while (tap.getNodeGauches() != null)

								{
									// System.out.println("---Tap=" +
									// tap.getCaractere() + " mot=" +
									// mot.charAt(i));
									if (tap.getNodeGauches().getCaractere() == mot.charAt(i)) {
										b = true;
										tap = tap.getNodeGauches();
										break;
									}

									tap = tap.getNodeGauches();

								}

								// System.out.println("--" + tap.getCaractere()
								// + " " + mot.charAt(i) + " B=" + b);

								if (b == false) {
									NodePrec = tap;
									NodePrec.setNodeGauches(createNoeud(mot.charAt(i), code));
									NodePrec = NodePrec.getNodeGauches();
									// System.out.println(".." +
									// NodePrec.getCaractere());
								} else {

									NodePrec = tap;

								}

							}
						}
					} else {
						if (NodePrec != null) {
							// System.out.println("Mot suivant=" +
							// mot.charAt(i));

							if (NodePrec.getNodeDroit() == null) {
								NodePrec.setNodeDroit(createNoeud(mot.charAt(i), code));
								NodePrec = NodePrec.getNodeDroit();
							}
							if (NodePrec.getNodeDroit() != null) {

								if (NodePrec.getNodeDroit().getCaractere() == mot.charAt(i)) {
									NodePrec = NodePrec.getNodeDroit();
								} else if (NodePrec.getNodeDroit().getCaractere() != mot.charAt(i)) {
									if (NodePrec.getNodeDroit().getNodeGauches() != null) {
										Noeud tap = NodePrec.getNodeDroit();
										boolean b = false;
										// System.out.println("TAB=" +
										// tap.getCaractere());
										while (tap.getNodeGauches() != null)

										{
											// System.out
											// .println("---Tap=" +
											// tap.getCaractere() + " mot=" +
											// mot.charAt(i));
											if (tap.getNodeGauches().getCaractere() == mot.charAt(i)) {
												b = true;
												tap = tap.getNodeGauches();
												break;
											}

											tap = tap.getNodeGauches();

										}

										// System.out
										// .println("***" + tap.getCaractere() +
										// " " + mot.charAt(i) + " B=" + b);

										if (b == false) {
											NodePrec = tap;
											NodePrec.setNodeGauches(createNoeud(mot.charAt(i), code));
											NodePrec = NodePrec.getNodeGauches();
											// System.out.println(".." +
											// NodePrec.getCaractere());
										} else {

											NodePrec = tap;

										}

									} else {
										NodePrec.getNodeDroit().setNodeGauches(createNoeud(mot.charAt(i), code));
										NodePrec = NodePrec.getNodeDroit().getNodeGauches();
									}

								}
							}

						}
					}

				}

				System.out.println(ligne);

			}
			br.close();

		}

		catch (Exception e) {
			System.out.println(e);
		}

		System.out.println(getracine().getCaractere());

		Noeud n = racine;

		printtree(n);

		return getracine();
	}

	public static int getCode(String mot, Noeud racine) {

		Noeud actuel = racine;
		int i = 0;
		boolean b = false;
		// System.out.println("Mot==" + mot);
		for (int j = 0; j < mot.length(); j++) {

			// System.out.println(mot.charAt(j) + "//" + actuel.getCaractere());

			if (mot.charAt(j) != actuel.getCaractere()) {

				while (actuel.getNodeGauches() != null) {
					// System.out.println("**" +
					// actuel.getNodeGauches().getCaractere() + "/" +
					// mot.charAt(j));
					actuel = actuel.getNodeGauches();

					if (actuel.getCaractere() == mot.charAt(j)) {
						b = true;
						break;
					}

				}

				if (b == true) {
					// System.out.println("TRUE=" + actuel.getCaractere());

					// System.out.println("**i** " + actuel.getCaractere() + "//
					// " + mot.charAt(mot.length() - 1) + " //"
					// + actuel.getCode());
					if (j < mot.length() - 1) {
						actuel = actuel.getNodeDroit();
					}
					// System.out.println("Lettre trouve " +
					// actuel.getCaractere());
				} else {
					// System.out.println("Lettre " + mot.charAt(j) + " n'existe
					// pas dans la lexique");
				}

			} else {
				// System.out.println("SUIVVVANT");
				// System.out.println("*e*** " + actuel.getCaractere() + "// " +
				// mot.charAt(mot.length() - 1) + " //"
				// + actuel.getCode());
				if (j < mot.length() - 1) {
					actuel = actuel.getNodeDroit();
				}

				// System.out.println("ACTUEL=" + actuel.getCaractere());
			}

		}
		// System.out.println("FIN=" + actuel.getCaractere() + " " +
		// actuel.getCode());

		// System.out.println("Suivant =" +
		// actuel.getNodeDroit().getCaractere());

		return actuel.getCode();

	}

	public static void main(String[] args) {

		Noeud racine = CreationArbre("lexique1.txt");
		// System.out.println(racine.getCaractere());
		int code = getCode("abrutissement", racine);

		System.out.println("Code=" + code);

	}

}
