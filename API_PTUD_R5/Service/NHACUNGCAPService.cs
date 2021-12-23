// <snippet_BookServiceClass>
using API_PTUD_R5.Model;
using MongoDB.Driver;
using System.Collections.Generic;
using System.Linq;

namespace API_PTUD_R5.Service
{
    public class NHACUNGCAPService
    {
        private readonly IMongoCollection<NHACUNGCAP> _books;

        // <snippet_BookServiceConstructor>
        public NHACUNGCAPService(IDatabaseSettings settings)
        {
            var client = new MongoClient(settings.ConnectionString);
            var database = client.GetDatabase(settings.DatabaseName);

            _books = database.GetCollection<NHACUNGCAP>(settings.NhaCungCapCollectionName);
        }
        // </snippet_BookServiceConstructor>

        public List<NHACUNGCAP> Get() =>
            _books.Find(book => true).ToList();

        public NHACUNGCAP Get(string id) =>
            _books.Find<NHACUNGCAP>(book => book.Id == id).FirstOrDefault();

        public NHACUNGCAP Create(NHACUNGCAP book)
        {
            _books.InsertOne(book);
            return book;
        }

        public void Update(string id, NHACUNGCAP bookIn) =>
            _books.ReplaceOne(book => book.Id == id, bookIn);

        public void Remove(NHACUNGCAP bookIn) =>
            _books.DeleteOne(book => book.Id == bookIn.Id);

        public void Remove(string id) =>
            _books.DeleteOne(book => book.Id == id);
    }
}
// </snippet_BookServiceClass>
