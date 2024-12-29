import requests
from bs4 import BeautifulSoup
import re
import json
import csv
import xml.etree.ElementTree as ET
from xml.dom import minidom

# Chemins des fichiers
json_path = r"D:\ISMAIL\3eme\SOC\Projet\Web services\SOAP\SOAP-WS\src\main\resources\data.json"
csv_path = r"D:\ISMAIL\3eme\SOC\Projet\Web services\SOAP\SOAP-WS\src\main\resources\data.csv"
xml_path = r"D:\ISMAIL\3eme\SOC\Projet\Web services\SOAP\SOAP-WS\src\main\resources\data.xml"

# Fonction pour générer toutes les URL des pages
def get_all_pages():
    urls = []
    for page_number in range(1, 164):  # 163 pages
        urls.append(f"https://www.barreaudenice.com/annuaire/avocats/?fwp_paged={page_number}")
    return urls

# Fonction pour extraire les informations des avocats d'une page
def parse_attorney(url):
    r = requests.get(url)
    soup = BeautifulSoup(r.content, "html.parser")
    avocats = soup.find_all("div", class_='callout secondary annuaire-single')
    attorneys = []
    
    for avocat in avocats:
        try:
            # Extraire les données principales
            name = avocat.find('h3').text.strip()
            raw_address = avocat.find('span', class_='adresse').text.strip()
            telephone = avocat.find('span', class_='telephone').text.strip().replace("T .", "").strip()
            email = avocat.find('span', class_='email').a.text.strip()
            
            # Nettoyer et découper l'adresse
            final_address = re.sub(r"\s+", " ", raw_address)
            address_parts = final_address.split(",")  # Séparation par virgule

            # Construire l'objet adresse
            address_obj = {
                "rue": address_parts[0].strip() if len(address_parts) > 0 else "",
                "code_postal_ville": address_parts[1].strip() if len(address_parts) > 1 else "",
                "complet": final_address
            }

            # Ajouter les données au tableau
            attorneys.append({
                "nom_prenom": name,
                "adresse": address_obj,
                "telephone": telephone,
                "email": email
            })
        except Exception as e:
            print(f"Erreur pour un avocat sur {url}: {e}")
    
    return attorneys

# Fonction pour sauvegarder les données en XML avec formatage
def save_to_xml(data):
    root = ET.Element("avocats")

    for attorney in data:
        avocat_elem = ET.SubElement(root, "avocat")

        # Ajouter les éléments
        ET.SubElement(avocat_elem, "nom_prenom").text = attorney["nom_prenom"]

        # Ajouter l'adresse comme un sous-élément
        adresse_elem = ET.SubElement(avocat_elem, "adresse")
        ET.SubElement(adresse_elem, "rue").text = attorney["adresse"]["rue"]
        ET.SubElement(adresse_elem, "code_postal_ville").text = attorney["adresse"]["code_postal_ville"]
        ET.SubElement(adresse_elem, "complet").text = attorney["adresse"]["complet"]

        ET.SubElement(avocat_elem, "telephone").text = attorney["telephone"]
        ET.SubElement(avocat_elem, "email").text = attorney["email"]

    # Formater l'arbre XML avec xml.dom.minidom pour obtenir des indentations
    xml_string = ET.tostring(root, encoding="utf-8")
    dom = minidom.parseString(xml_string)
    formatted_xml = dom.toprettyxml(indent="    ")

    # Écrire dans le fichier XML
    with open(xml_path, "w", encoding="utf-8") as xml_file:
        xml_file.write(formatted_xml)
    print(f"Les données ont été sauvegardées dans {xml_path}.")

# Fonction principale pour collecter les données et les sauvegarder
def scrape_and_save():
    all_attorneys = []
    urls = get_all_pages()
    
    for url in urls:
        print(f"Scraping {url}...")
        attorneys = parse_attorney(url)
        all_attorneys.extend(attorneys)
    
    # Sauvegarder les données dans un fichier JSON
    with open(json_path, "w", encoding="utf-8") as f:
        json.dump(all_attorneys, f, indent=4, ensure_ascii=False)
    print(f"Les données ont été sauvegardées dans {json_path}.")

    # Sauvegarder les données dans un fichier CSV
    with open(csv_path, "w", encoding="utf-8", newline="") as csv_file:
        writer = csv.writer(csv_file)
        # Écrire les en-têtes
        writer.writerow(["Nom Prénom", "Rue", "Code Postal et Ville", "Adresse Complète", "Téléphone", "Email"])
        
        # Écrire les données
        for attorney in all_attorneys:
            writer.writerow([
                attorney["nom_prenom"],
                attorney["adresse"]["rue"],
                attorney["adresse"]["code_postal_ville"],
                attorney["adresse"]["complet"],
                attorney["telephone"],
                attorney["email"]
            ])
    print(f"Les données ont été sauvegardées dans {csv_path}.")

    # Sauvegarder les données dans un fichier XML
    save_to_xml(all_attorneys)

# Lancer le script
scrape_and_save()
